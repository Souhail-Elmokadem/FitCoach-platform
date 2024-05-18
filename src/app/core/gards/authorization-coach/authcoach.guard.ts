import { Injectable, inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { AuthService } from "../../services/auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class authcoachGuard {

  
  constructor(private router: Router,private authservice:AuthService) {

  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
    
    
      if(!this.authservice.IsLogged()){
        this.router.navigateByUrl("auth/login")
          return false;
      }
      if (this.authservice.sessiondata.roles=="COACH") {
      return true;
    } else{
      this.router.navigateByUrl("client/service")
      return false;

    }
    
    
   

  }
}


export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(authcoachGuard).canActivate(next, state);
}
