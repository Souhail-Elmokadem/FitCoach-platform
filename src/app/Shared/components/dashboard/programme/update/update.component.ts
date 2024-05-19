import { Component } from '@angular/core';
import { Client } from '../../../../models/Client';
import { ProgramService } from '../../../../../core/services/program/program.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ClientService } from '../../../../../core/services/client/client.service';
import { map } from 'rxjs';
import { Program } from '../../../../models/Program';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrl: './update.component.css'
})
export class UpdateComponent {
hancleclickDelete() {
  const check = confirm("are sure to delete !");
  if(check){
    this.programservice.deleteProgram(this.program.id).subscribe({
      next:data=>{
        this.router.navigateByUrl('coach/service/program')
      },
      error:err=>console.log(err)
    })
  }
}


  currentIndex: number = 0;
  attachment?: File;
  URL: any;
  program!:Program;
  formProgram!: FormGroup;
  clientlist:Array<Client> = [];
  size:number=3;
  currentPage:number=0;
  kayword:string="";
  clientAffected:Array<Client> = []
  constructor(private fb: FormBuilder,private clientservice:ClientService
    , private programservice: ProgramService, private router: Router,private activeRoute:ActivatedRoute) {
 this.formProgram = this.fb.group({
      name: [''],
      description: [''],
      duree: [''],
      seance: [''],
      objectifs: ['']
    })
   
  }
  ngOnInit(): void {
   let progId = this.activeRoute.snapshot.params['id'];
    this.getListClients();
    this.getProgramById(progId);

    
  }
  
  getProgramById(id:number){
      this.programservice.getProgramById(id).subscribe({
        next:data=>{
          this.program=data
          this.InitialiserForm();
        },
        error:err=>console.log(err)
      })
  }
  handleSubmit() {
    if(this.attachment != undefined) {
      this.programservice.updateProgram(this.program.id,this.formProgram,this.clientAffected).subscribe({
      next: (data:any) => { 
        console.log(data)
        this.router.navigateByUrl('coach/service/program')
        
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
  handleAffect(clientparam:Client) {
     console.log(clientparam)
     this.kayword=''
     const exists = this.clientAffected.some(client => client.id === clientparam.id);
     if(!exists){
      this.clientAffected.push(clientparam)
     }
     
  }
  handlesearch(event: any) {
    this.getListClients();
    
  }
InitialiserForm() {
  this.formProgram = this.fb.group({
    name: this.program.nom,
    description: this.program.description,
    duree: this.program.duree,
    seance: [4],
    objectifs: this.program.objectifs
  })
  this.clientAffected=this.program.members;
  
}
removeClientAffected(clientId: number): void {
  this.clientAffected = this.clientAffected.filter(client => client.id !== clientId);
}
}


 

