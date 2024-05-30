import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { Person } from '../../../models/Person';
import { ImageService } from '../../../../core/services/image/image.service';

@Component({
  selector: 'app-profile-client',
  templateUrl: './profile-client.component.html',
  styleUrl: './profile-client.component.css'
})
export class ProfileClientComponent implements OnInit {
 
  person!:Person;
  editMode:boolean=false;
  imagesrc!:string;
  constructor(private authservice:AuthService,private imageservice:ImageService){ }
  
  ngOnInit(): void {
    this.getProfile();
  
  }
  getProfile() {
    this.authservice.getPersonWithEmail().subscribe({
      next:data=>{
        this.person=data
        this.getImage(this.person.avatar)
      },
      error:err=>console.log(err)
    })
  }
  getImage(avatar: string) {
    this.imageservice.getImage(avatar).subscribe({
      next:data=>this.imagesrc=data,
      error:err=>console.log(err)
    })
  }

}
