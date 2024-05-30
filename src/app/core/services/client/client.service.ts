import { HttpClient, HttpHandler, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Client } from '../../../Shared/models/Client';
import { Observable } from 'rxjs';
import { Coach } from '../../../Shared/models/Coach';
import { AuthService } from '../auth/auth.service';


@Injectable({
  providedIn: 'root'
})
export class ClientService {
  
  
  constructor(private http:HttpClient,private authservice:AuthService){ }

  apiUrl = environment.baseUrl
  
  public getClients(keyword:string,page:number,size:number):Observable<Array<Client>>{
    return this.http.get<Array<Client>>(`${this.apiUrl}/client/list?Search=${keyword}&page=${page}&size=${size}`);
  }
  public getClientsKeyword(keyword:string):Observable<Array<Client>>{
    return this.http.get<Array<Client>>(`${this.apiUrl}/client/list?Search=${keyword}`);
  }
  public listClientCoach(keyword:string,currentpage:number,size:number):Observable<Array<Client>>{
    return this.http.get<Array<Client>>(this.apiUrl+`/client/listClientByCoach?Search=${keyword}&page=${currentpage}&size=${size}&coachemail=${this.authservice.sessiondata.username}`)
}
  public listClientbyCoach(size:number,coach:Coach):Observable<Array<Client>>{
    return this.http.get<Array<Client>>(this.apiUrl+`/client/listClientByCoach?&coachemail=${coach.email}`)
    // http://localhost:9090/client/listClientByCoach?coachemail=coach@gmail.com
  }
  public enroll(coach:Coach){
    const formdata = new FormData();
    formdata.append("clientemail",this.authservice.sessiondata.username)
    formdata.append("coachemail",coach.email)
    return this.http.post(this.apiUrl+'/client/enroll',formdata)
  }
    

}
