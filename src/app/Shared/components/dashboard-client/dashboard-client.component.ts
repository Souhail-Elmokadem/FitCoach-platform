import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth/auth.service';

@Component({
  selector: 'app-dashboard-client',
  templateUrl: './dashboard-client.component.html',
  styleUrl: './dashboard-client.component.css',
  encapsulation: ViewEncapsulation.None
})
export class DashboardClientComponent implements OnInit{
  constructor(public route:Router,public authservice:AuthService){}
  ngOnInit(): void {
    
  }

}
