import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {


formRegister!: FormGroup;
constructor(private fb:FormBuilder,private authservice:AuthService,private router:Router){}
ngOnInit(): void {
  this.formRegister = this.fb.group({
    firstName : [''],
    lastName : [''],
    email : [''],
    role : ['USER'] ,
    password : ['']
  })
}
handleRegister() {
  this.authservice.Register(this.formRegister.value).subscribe({
    next : data=> this.router.navigateByUrl("auth/login"),
    error : err => console.log(err)
  });
}

}
