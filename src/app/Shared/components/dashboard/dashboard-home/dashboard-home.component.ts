import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../../core/services/client/client.service';
import { Client } from '../../../models/Client';
import { ProgramService } from '../../../../core/services/program/program.service';

@Component({
  selector: 'app-dashboard-home',
  templateUrl: './dashboard-home.component.html',
  styleUrl: './dashboard-home.component.css'
})
export class DashboardHomeComponent implements OnInit {
  newusers:number = 0;
  totalMembers!:number;
  listClient:Array<Client>=[];
  listnewClient:Array<Client>=[];
  totalPrograms:number=0;
  constructor(private clientservice:ClientService,private programservice:ProgramService) { }
  
  ngOnInit(): void {
    this.getTotalMembers();
    this.getPrograms();
  }
  getTotalMembers(){
    this.clientservice.listClientCoach("",0,5).subscribe({
      next:(data:any)=>{
        this.listClient=data.items;
        this.totalMembers=data.totalItems;
        this.getNewClient(this.listClient);
      },
      error:err=>console.log(err)
    })
  }
  getNewClient(listClient: Array<Client>): void {
    const today = new Date();
    const todayDateOnly = new Date(today.getFullYear(), today.getMonth(), today.getDate());

    
    listClient.forEach(cl => {
        const createdAtDate = new Date(cl.createdAt);
        const clientDateOnly = new Date(createdAtDate.getFullYear(), createdAtDate.getMonth(), createdAtDate.getDate());
        if (clientDateOnly.getDate() === todayDateOnly.getDate()) {
            this.listnewClient.push(cl);
            this.newusers++;
        }
        
    });
}
  getPrograms(){
    this.programservice.getProgramsCoach("",10,0).subscribe({
      next:(data:any) => {
        this.totalPrograms=data.totalItems;
      },
      error:err=>console.log(err)
    })
  }

}
