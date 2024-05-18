import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProgramService } from '../../../../core/services/program/program.service';
import { Router } from '@angular/router';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../../core/services/client/client.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-progemail?: stringemail: stringram-create',
  templateUrl: './program-create.component.html',
  styleUrl: './program-create.component.css'
})
export class ProgramCreateComponent implements OnInit {





  currentIndex: number = 0;
  attachment?: File;
  URL: any;
  formProgram!: FormGroup;
  clientlist:Array<Client> = [];
  size:number=3;
  currentPage:number=0;
  kayword:string="";
  clientAffected:Array<Client> = []
  constructor(private fb: FormBuilder,private clientservice:ClientService
    , private programservice: ProgramService, private router: Router) {
 this.formProgram = this.fb.group({
      name: [''],
      description: [''],
      duree: [''],
      seance: [''],
      objectifs: ['']
    })
   
  }
  ngOnInit(): void {
   
    this.getListClients();
  }
  handleSubmit() {
    if(this.attachment != undefined) {
      this.programservice.addProgram(this.formProgram, this.attachment,this.clientAffected).subscribe({
      next: data => { 
        console.log(data)
        this.router.navigateByUrl('coach/service/program')
        this.formProgram.reset();
       }
    })
    }
    
  }
  hancleclick() {
    if (this.currentIndex < 2 && this.currentIndex > -1) {
      this.currentIndex++;
    }
    console.log(this.currentIndex);
  }
  hancleclickBack() {
    if (this.currentIndex < 3 && this.currentIndex > 0) {
      this.currentIndex--;
    }
  }

  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      const file: File = inputElement.files[0];
      this.attachment = file;

    }
  }

  getListClients(){
      this.clientservice.listClientCoach(this.kayword,this.currentPage,this.size).pipe(
        map((data:any)=>data.items)
      ).subscribe({
        next:(data:any)=>this.clientlist=data,
        error:err=>console.log(err)
      })
  }
  handleAffect(client:Client) {
     console.log(client)
     this.kayword=''
     this.clientAffected.push(client)
  }
  handlesearch(event: any) {
    this.getListClients();
    
  }

}
