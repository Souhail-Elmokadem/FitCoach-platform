import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coach } from '../../../Shared/models/Coach';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CoachService {

  ApiUrl = environment.baseUrl

  constructor(private http:HttpClient) { }


  public listCoachs(keyword:string,currentpage:number,size:number):Observable<Array<Coach>>{
      return this.http.get<Array<Coach>>(this.ApiUrl+`/coach/list?Search=${keyword}&page=${currentpage}&size=${size}`)
  }
}
