import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { AuthService } from '../services/auth/auth.service';

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {
  constructor(private authservice: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!req.url.includes("/auth")  && this.authservice.IsLogged()) {
      console.log(req.url)
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authservice.getAccessToken()}`
        }
      });

      return next.handle(authReq).pipe(
        catchError((error) => {
          if (error.status === 401 && this.authservice.IsLogged()) {
            return this.authservice.loginWithRefreshToken().pipe(
              switchMap((d) => {
                this.authservice.loadUser(d);
                const authReq = req.clone({
                  setHeaders: {
                    Authorization: `Bearer ${this.authservice.getAccessToken()}`
                  }
                });
                return next.handle(authReq);
              })
            );
          }
          return throwError(error);
        })
      );
    } else {
      return next.handle(req);
    }
  }
}
