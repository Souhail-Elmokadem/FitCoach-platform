import { Component, OnInit } from '@angular/core';
import { ProgramService } from '../../../../core/services/program/program.service';
import { Program } from '../../../models/Program';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrl: './program.component.css'
})
export class ProgramComponent implements OnInit {
  program!:Program;
  datastatus:boolean=false;
  constructor(private programservice:ProgramService){ }
  
  ngOnInit(): void {
    this.getProgram();
  }
  getProgram(){
    this.programservice.getProgramCLient().subscribe(
      {
      next: data=>{
        this.program=data;
        this.datastatus=true;
      },
      error:err=>console.log(err)
    }
  )

}
}
