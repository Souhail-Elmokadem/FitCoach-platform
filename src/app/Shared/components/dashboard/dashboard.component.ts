import { AfterContentChecked, AfterViewChecked, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  encapsulation: ViewEncapsulation.None
})
export class DashboardComponent implements OnInit,AfterViewChecked{

  parentKw: string="";
  constructor(public route:Router,public authservice:AuthService){

  }
  ngAfterViewChecked(): void {
    
  }
  ngOnInit(): void {
  }
  onKeywordChange(event: any) {
    this.parentKw = event.target.value;
  }


}
