import { HttpClient, HttpHandler, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { SessionData } from '../../../Shared/models/SessionData';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { Client } from '../../../Shared/models/Client';
import { Observable } from 'rxjs';
import { Person } from '../../../Shared/models/Person';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = environment.baseUrl


  public getAccessToken() {
    return this.sessiondata.jwtToken;
  }
  public loginWithRefreshToken() {
    const body = {
      logintype: 'refreshToken',
      refreshToken: this.sessiondata.jwtRefreshToken
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post(this.apiUrl + '/auth/login', body, { headers: headers });
  }





  sessiondata!: SessionData;
  constructor(private http: HttpClient, private router: Router) {
    this.sessiondata = new SessionData(false, null, null, '', '');

  }
  getToken(): string | null {
    return localStorage.getItem('access-token');
  }

  public Login(email: string, password: string) {
    const loginform = {
      "logintype": "pass",
      "email": email,
      "password": password
    }
    const header = new HttpHeaders().set("Content-Type", "application/json");
    return this.http.post(this.apiUrl + "/auth/login", loginform, { headers: header })
  }
  public Register(client: Client) {
    const header = new HttpHeaders().set("Content-Type", "application/json");
    return this.http.post(this.apiUrl + "/auth/register", client, { headers: header })
  }
  getPersonWithEmail():Observable<Person>{
    return this.http.get<Person>(this.apiUrl+"/person?email="+this.sessiondata.username)
  }
  getPersonWithEmailParam(email:string):Observable<Person>{
    return this.http.get<Person>(this.apiUrl+"/person?email="+email)
  }

  public loadUser(data: any) {
    this.sessiondata.jwtToken = data['access-token'];
    this.sessiondata.jwtRefreshToken = data['refresh-token'];
    if (this.sessiondata.jwtToken) {
      this.sessiondata.IsAuthenticated = true;
      let jwt: any = jwtDecode(this.sessiondata.jwtToken);
      this.sessiondata.username = jwt.sub;
      this.sessiondata.roles = jwt.scope;
      window.localStorage.setItem("access-token", this.sessiondata.jwtToken);
      window.localStorage.setItem("refresh-token", this.sessiondata.jwtRefreshToken)
    }
  }
  public IsLogged(): boolean {
    return this.sessiondata.IsAuthenticated;
  }
  public Logout() {
    this.sessiondata = new SessionData(false, undefined, undefined, "", "");
    window.localStorage.removeItem("access-token");
    window.localStorage.removeItem("refresh-token");
    this.router.navigateByUrl("auth/login");
  }
  public editPerson(avatar: File, formPerson: FormGroup): Observable<any> {
    const formData = new FormData();
    
    
    formData.append('email', formPerson.get('email')?.value);
    formData.append('firstName', formPerson.get('firstName')?.value);
    formData.append('lastName', formPerson.get('lastName')?.value);
    formData.append('profile', formPerson.get('profile')?.value);
     const headers = new HttpHeaders();
     headers.append('Content-Type', 'multipart/form-data');
     if(avatar){
      formData.append('avatar', avatar);
     }
    return this.http.put(`${this.apiUrl}/person`, formData);
  }

}
