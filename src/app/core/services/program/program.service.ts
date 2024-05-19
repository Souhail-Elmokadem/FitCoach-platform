import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Program } from '../../../Shared/models/Program';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Form, FormGroup } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { Client } from '../../../Shared/models/Client';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {
 
 

  
  constructor(private http:HttpClient,private authservice:AuthService) { }

  ApiUrl = environment.baseUrl;

  public getPrograms(keyword:string,size:number,currentpage:number):Observable<Array<Program>>{
    return this.http.get<Array<Program>>(this.ApiUrl+`/program/list?Search=${keyword}&page=${currentpage}&size=${size}`)

  }
  public getProgramById(id:number):Observable<Program>{
    return this.http.get<Program>(this.ApiUrl+"/program/"+id);
  }
  public getProgramCLient():Observable<Program>{
    return this.http.get<Program>(this.ApiUrl+'/program/programClient/'+this.authservice.sessiondata.username)
  }
  public getProgramsCoach(keyword:string,size:number,currentpage:number):Observable<Array<Program>>{
    return this.http.get<Array<Program>>(this.ApiUrl+`/program/listprogrambycoach?Search=${keyword}&page=${currentpage}&size=${size}&coachemail=${this.authservice.sessiondata.username}`)
  }
  public addProgram(program:FormGroup,file:File,clients:Array<Client>){
    const formData: FormData = new FormData();
    formData.append('nom', program.value.name);
    formData.append('description', program.value.description);
    formData.append('duree', program.value.duree);
    formData.append('seance', program.value.seance);
    formData.append('objectifs', program.value.objectifs);
    formData.append('coache', this.authservice.sessiondata.username);
    formData.append('attachment', file);
    formData.append('clients',JSON.stringify(clients));
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    return this.http.post(this.ApiUrl+'/program/create',formData,{headers:headers});
  }
  public updateProgram(idprog: number, formProgram: FormGroup<any>, clientAffected: Client[]) {
    const formdata = new FormData();
    formdata.append('id', idprog.toString());
    formdata.append('nom', formProgram.value.name);
    formdata.append('description', formProgram.value.description);
    formdata.append('duree', formProgram.value.duree);
    formdata.append('seance', formProgram.value.seance);
    formdata.append('objectifs', formProgram.value.objectifs);
    formdata.append('clients',JSON.stringify(clientAffected));
    return this.http.put(this.ApiUrl+"/program/edit",formdata)
  }

  public deleteProgram(id:number){
    

    return this.http.delete(this.ApiUrl+'/program/'+id)
  }
  // addProgramDet(program: FormGroup<any>) {
  //   const formData: FormData = new FormData();
  //   formData.append('nom', program.value.name);
  //   formData.append('description', program.value.description);
  //   formData.append('duree', program.value.duree);
  //   formData.append('seance', program.value.seance);
  //   formData.append('objectifs', program.value.objectifs);
  //   formData.append('coache', "ahmed@google");
  //   const headers = new HttpHeaders();
  //   headers.append('Content-Type', 'multipart/form-data');
  //   return this.http.post(this.ApiUrl+'/program/create',formData,{headers:headers});
  // }
}
