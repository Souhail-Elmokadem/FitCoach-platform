import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-client',
  templateUrl: './dashboard-client.component.html',
  styleUrl: './dashboard-client.component.css',
  encapsulation: ViewEncapsulation.None
})
export class DashboardClientComponent implements OnInit{
  constructor(public route:Router){}
  ngOnInit(): void {
    
  }

}
