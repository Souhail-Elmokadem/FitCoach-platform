import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { Person } from '../../../models/Person';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{
 
  person!:Person;
  editMode:boolean=false;
  constructor(private authservice:AuthService){ }
  
  ngOnInit(): void {
    this.getProfile();
  }
  getProfile() {
    this.authservice.getPersonWithEmail().subscribe({
      next:data=>{
        this.person=data
        
      },
      error:err=>console.log(err)
    })
  }

}
