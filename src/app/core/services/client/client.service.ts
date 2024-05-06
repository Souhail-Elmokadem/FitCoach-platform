import { HttpClient, HttpHandler, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Client } from '../../../Shared/models/Client';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ClientService {
  constructor(private http:HttpClient){ }

  apiUrl = environment.baseUrl
  
  public getClients(keyword:string,page:number,size:number):Observable<Array<Client>>{
    return this.http.get<Array<Client>>(`${this.apiUrl}/client/list?Search=${keyword}&page=${page}&size=${size}`);
  }
  public getClientsKeyword(keyword:string):Observable<Array<Client>>{
    return this.http.get<Array<Client>>(`${this.apiUrl}/client/list?Search=${keyword}`);
  }
}
