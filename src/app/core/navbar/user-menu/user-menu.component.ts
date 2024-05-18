import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrl: './user-menu.component.css'
})
export class UserMenuComponent implements OnInit {
  

  dropdown:boolean=false;
  constructor(public authservice:AuthService){ }

  ngOnInit(): void {
    
  }

}
