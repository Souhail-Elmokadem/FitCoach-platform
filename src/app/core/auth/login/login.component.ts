import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  formlogin!:FormGroup
  messageError!:string;
  constructor(private fb:FormBuilder,public authservice:AuthService,private router:Router){}
  ngOnInit(): void {
    this.formlogin = this.fb.group({
      email : [''],
      password : ['']
    })
  }
  handleLogin() {
    this.authservice.Login(this.formlogin.value.email,this.formlogin.value.password).subscribe({
      next:data=>{
        console.log(data)
        this.authservice.loadUser(data);
        this.router.navigateByUrl("/")
      },
      error:err=>this.messageError="The email or password you entered is incorrect."
    })
    }


}
