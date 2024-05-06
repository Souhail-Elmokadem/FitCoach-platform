import { Component, OnInit } from '@angular/core';
import { ClientService } from './core/services/client/client.service';
import { AuthService } from './core/services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
 
  title = 'fitCoach';

  constructor(private authservice:AuthService){}
 ngOnInit(): void {
  let accestoken = window.localStorage.getItem("access-token");
  let refreshtoken = window.localStorage.getItem("refresh-token");
  if(refreshtoken) this.authservice.loadUser({"access-token":accestoken,"refresh-token":refreshtoken})
    
  }

}
