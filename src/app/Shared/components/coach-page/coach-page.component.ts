import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth/auth.service';
import { CoachService } from '../../../core/services/coach/coach.service';
import { Coach } from '../../models/Coach';
import { Client } from '../../models/Client';
import { ClientService } from '../../../core/services/client/client.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-coach-page',
  templateUrl: './coach-page.component.html',
  styleUrl: './coach-page.component.css'
})
export class CoachPageComponent implements OnInit {

  coach!:Coach;
  mambersSize:number=6;
  listMembers:Array<Client>=[];
  coachClient!:Coach;
  constructor(private router:ActivatedRoute,
  public authservice:AuthService,
  private coachservice:CoachService,
  private clientservice:ClientService


){ }

  ngOnInit(): void {
    let coachid = this.router.snapshot.params['id']
    let coachFullName = this.router.snapshot.params['name']

    this.getCoachClient();

    if(coachid){
      this.getCoachWithId(coachid);
      
    }

  }
  getCoachWithId(id:number){
    this.coachservice.getCoachWithId(id).subscribe({
        next:(data)=>{
        if(data != null && data != undefined)
          this.coach=data
          this.getMembersByCoach(this.coach);

        },
        error:err=>console.log(err)
    })
  }
  getMembersByCoach(coach: Coach): void {
    if (this.coach) {
      this.clientservice.listClientbyCoach(this.mambersSize,coach).subscribe({
        next: (data: any) => {
          this.listMembers = data.items;
          this.getCoachClient();
        },
        error: err => console.log(err)
      });
    }
  }
    
  getCoachClient(){
    this.coachservice.getCoachClient().subscribe({
       next:(data:any)=>{
        if(data!=null){
              this.coachClient=data;

        }
       },
       error:err=>console.log('you don`t have any coach')
    });
  }
  handleEnroll(coach:Coach) {
      this.clientservice.enroll(coach).subscribe({
        next:data=>{
          this.getCoachClient();
          this.getMembersByCoach(coach)
          console.log('Enrolled success')
        
        },
        error:err=>console.log(err)
      })
  }


}
