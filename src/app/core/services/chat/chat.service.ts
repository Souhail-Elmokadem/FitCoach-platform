import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../../../Shared/models/Message';
import { environment } from '../../../../environments/environment';
import { AuthService } from '../auth/auth.service';
import { Chat } from '../../../Shared/models/Chat';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

   ApiUrl = environment.baseUrl;
  constructor(private http:HttpClient,private authservice:AuthService) { }

  public AddMessage(replay: string, content: string) {
    const params = new FormData();
    params.append("sender", this.authservice.sessiondata.username);
    params.append("replay", replay);
    params.append("content", content);
    return this.http.post(this.ApiUrl + `/chat/message`, params);
  }
  
  public getMessages(replay:string):Observable<Chat>{
    const params = new HttpParams();
    params.set("sender","coach@gmail.com")
    params.set("replay",replay)
      return this.http.get<Chat>(this.ApiUrl+`/chat/message?replay=${replay}&sender=`+this.authservice.sessiondata.username)
  }

}
