import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { Person } from '../../../models/Person';

@Component({
  selector: 'app-dashboard-client-home',
  templateUrl: './dashboard-client-home.component.html',
  styleUrl: './dashboard-client-home.component.css'
})
export class DashboardClientHomeComponent implements OnInit{
  
  person!:Person;
  constructor(private authservice:AuthService){ }
  
  ngOnInit(): void {
    
    this.getPerson();
  }
  getPerson() {
    this.authservice.getPersonWithEmail().subscribe({
      next:data=>this.person=data,
      error:err=>console.log(err)
    })
  }

}
