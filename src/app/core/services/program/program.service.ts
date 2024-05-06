import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Program } from '../../../Shared/models/Program';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Form, FormGroup } from '@angular/forms';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {
 

  
  constructor(private http:HttpClient,private authservice:AuthService) { }

  ApiUrl = environment.baseUrl;

  public getPrograms(keyword:string,size:number,currentpage:number):Observable<Array<Program>>{
    return this.http.get<Array<Program>>(this.ApiUrl+`/program/list?Search=${keyword}&page=${currentpage}&size=${size}`)

  }
  public addProgram(program:FormGroup,file:File){
    const formData: FormData = new FormData();
    formData.append('nom', program.value.name);
    formData.append('description', program.value.description);
    formData.append('duree', program.value.duree);
    formData.append('seance', program.value.seance);
    formData.append('objectifs', program.value.objectifs);
    formData.append('coache', "souhail@jgjhgj.com");
    formData.append('attachment', file);
    return this.http.post(this.ApiUrl+'/program/create',formData);
  }
  addProgramDet(program: FormGroup<any>) {
    const formData: FormData = new FormData();
    formData.append('nom', program.value.name);
    formData.append('description', program.value.description);
    formData.append('duree', program.value.duree);
    formData.append('seance', program.value.seance);
    formData.append('objectifs', program.value.objectifs);
    formData.append('coache', "ahmed@google");
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    return this.http.post(this.ApiUrl+'/program/create',formData,{headers:headers});
  }
}
