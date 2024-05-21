import { Component, OnInit } from '@angular/core';
import { Program } from '../../../models/Program';
import { ProgramService } from '../../../../core/services/program/program.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-programme',
  templateUrl: './programme.component.html',
  styleUrl: './programme.component.css'
})
export class ProgrammeComponent implements OnInit{



  currentIndex:number=0;
  programs:Array<Program>=[];
  keyword: string = "";
  currentPage: number = 0;
  size: number = 4;
  totalItems: number = 0;
  constructor(private programservice:ProgramService,private router:Router){
  }
  ngOnInit(): void {
          this.getPrograms();

  }

  public getPrograms(){
   this.programservice.getProgramsCoach(this.keyword,  this.size,this.currentPage).subscribe({
    next: (data: any) => {
      this.programs = data.items;
      this.totalItems = data.totalItems;
    },
    error: (err) => {
      console.log(err);
    }
  });
  }
  
  handleNext() {
    let pages = this.totalItems / this.size;
    if (this.currentPage < pages) {
      this.currentPage++;
      this.getPrograms();
    }
  }

  handlePrev() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getPrograms();
    }
  }
   handlesearch(newKeyword: string) {
    this.keyword = newKeyword;
    console.log(this.keyword);
    this.getPrograms(); // Update the clients based on the new keyword
  }

  handleEdit(p:Program) {
    this.router.navigateByUrl(`coach/service/program/edit/${p.id}/${p.nom.replace(' ','')}`)
    }

}
