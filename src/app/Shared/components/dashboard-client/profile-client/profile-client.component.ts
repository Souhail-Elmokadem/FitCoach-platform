import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { Person } from '../../../models/Person';

@Component({
  selector: 'app-profile-client',
  templateUrl: './profile-client.component.html',
  styleUrl: './profile-client.component.css'
})
export class ProfileClientComponent implements OnInit {
 
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
        console.log(this.person)
      },
      error:err=>console.log(err)
    })
  }

}
