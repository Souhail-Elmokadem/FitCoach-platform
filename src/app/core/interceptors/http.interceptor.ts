import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { AuthService } from '../services/auth/auth.service';

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {
  constructor(private authservice: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Check if the request URL does not contain "auth" and the user is logged in
    if (!req.url.includes("/auth") && this.authservice.IsLogged()) {
      // Clone the request and add Authorization header with token
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authservice.getAccessToken()}`
        }
      });

      // Pass the modified request through the next interceptor or the backend
      return next.handle(authReq).pipe(
        catchError((error) => {
          if (error.status == 401 && this.authservice.IsLogged()) {
            // Token expired, try to refresh token
            return this.authservice.loginWithRefreshToken().pipe(
              
              switchMap((d) => {
                this.authservice.loadUser(d);
                // Retry the request with the new token
                const authReq = req.clone({
                  setHeaders: {
                    Authorization: `Bearer ${this.authservice.getAccessToken()}`,
                  },
                });
                return next.handle(authReq);
              }),
            );
          }
          // Throw error if not 401 or user is not logged in
          return throwError(error);
        })
      );
    } else {
      // Pass the original request through if it's not for authentication or user is not logged in
      return next.handle(req);
    }
  }
}
