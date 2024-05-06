import { Component, OnInit } from '@angular/core';
import { Program } from '../../../models/Program';
import { ProgramService } from '../../../../core/services/program/program.service';

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
  size: number = 3;
  totalItems: number = 0;
  constructor(private programservice:ProgramService){
  }
  ngOnInit(): void {
          this.getPrograms();

  }

  public getPrograms(){
   this.programservice.getPrograms(this.keyword,  this.size,this.currentPage).subscribe({
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

}
