import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProgramService } from '../../../../core/services/program/program.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-program-create',
  templateUrl: './program-create.component.html',
  styleUrl: './program-create.component.css'
})
export class ProgramCreateComponent implements OnInit {


  currentIndex: number = 0;
  attachment?: File;
  URL: any;
  formProgram!: FormGroup
  constructor(private fb: FormBuilder, private programservice: ProgramService, private router: Router) {
 
  }
  ngOnInit(): void {
   this.formProgram = this.fb.group({
      name: [''],
      description: [''],
      duree: [''],
      seance: [''],
      objectifs: ['']
    })
  }
  handleSubmit() {
    if(this.attachment != undefined) {
      this.programservice.addProgram(this.formProgram, this.attachment).subscribe({
      next: data => { 
        console.log(data)
        this.router.navigateByUrl('service/program')
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


}
