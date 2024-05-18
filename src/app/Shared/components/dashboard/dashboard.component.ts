import { AfterContentChecked, AfterViewChecked, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  encapsulation: ViewEncapsulation.None
})
export class DashboardComponent implements OnInit,AfterViewChecked{

  parentKw: string="";
  constructor(public route:Router){

  }
  ngAfterViewChecked(): void {
    
  }
  ngOnInit(): void {
  }
  onKeywordChange(event: any) {
    this.parentKw = event.target.value;
  }


}
