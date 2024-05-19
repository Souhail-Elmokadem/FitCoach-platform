import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthentificationGuard } from '../gards/authentification.guard';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  
  
  @ViewChild('targetSection') targetSection!: ElementRef;

  constructor(private router:Router,public authservice:AuthService){}
  
  ngOnInit(): void {
  }
  isNavbarVisible(): boolean {
    return !this.router.url.includes("auth");
  }
  hideNavbarService():boolean{
    return !this.router.url.includes("service");
  }
  scrollToSection(): void {
    if (this.targetSection) {
      this.targetSection.nativeElement.scrollIntoView({ behavior: 'smooth' });
    }
  }
 

    
  
}
