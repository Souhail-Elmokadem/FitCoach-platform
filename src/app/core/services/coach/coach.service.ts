import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, filter } from 'rxjs';
import { Coach } from '../../../Shared/models/Coach';
import { environment } from '../../../../environments/environment';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CoachService {

  ApiUrl = environment.baseUrl

  constructor(private http:HttpClient,private authservice:AuthService) { }


  public listCoachs(keyword:string,currentpage:number,size:number):Observable<Array<Coach>>{
      return this.http.get<Array<Coach>>(this.ApiUrl+`/coach/list?Search=${keyword}&page=${currentpage}&size=${size}`)
  }

  public getCoachWithId(id:number):Observable<Coach>{
      return this.http.get<Coach>(this.ApiUrl+'/coach/'+id)
  }
  public getCoachClient():Observable<Coach> {
    const params = new HttpParams().append("clientemail",this.authservice.sessiondata.username)
    return this.http.get<Coach>(this.ApiUrl+'/coach/coachbyclient',{params:params});
  }

}
