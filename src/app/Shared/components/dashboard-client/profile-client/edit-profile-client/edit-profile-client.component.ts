import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Person } from '../../../../models/Person';
import { AuthService } from '../../../../../core/services/auth/auth.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Route, Router } from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-edit-profile-client',
  templateUrl: './edit-profile-client.component.html',
  styleUrl: './edit-profile-client.component.css'
})
export class EditProfileClientComponent implements OnInit {
  selectedFile!: File;
  imageUrl!: string;
  
  person!: Person
  formPerson!: FormGroup;
  @ViewChild('fileInput') fileInput!: ElementRef;

  constructor(private authservice: AuthService, private fb: FormBuilder
    ,private route:Router,private _location:Location) {
    this.formPerson = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      profile: ['']
    })
  }

  ngOnInit(): void {
    this.getPerson();

  }
  triggerFileInputClick(): void {
    this.fileInput.nativeElement.click();
  }
  getPerson() {
    this.authservice.getPersonWithEmail().subscribe({
      next: (data: any) => {
        this.person = data;
        this.remplirForm(this.person);
        this.imageUrl=this.person.avatar
      },
      error: err => console.log(err)
    })
  }
  editPerson() {
          this.authservice.editPerson(this.selectedFile,this.formPerson).subscribe({
          next:data=>{
          console.log(data)},
          error:err=>console.log(err)
        })
  }

  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      const file: File = inputElement.files[0];
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imageUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    }

  }
  remplirForm(person: Person) {
    this.formPerson = this.fb.group({
      firstName: person.firstName,
      lastName: person.lastName,
      email: person.email,
      profile: person.profile
    })
  }

  handleSubmit() {
    this.editPerson();
    this._location.back();
  }

}

