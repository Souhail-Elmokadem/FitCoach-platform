import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ChatService } from '../../../../core/services/chat/chat.service';
import { Chat } from '../../../models/Chat';
import { Message } from '../../../models/Message';
import { CoachService } from '../../../../core/services/coach/coach.service';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { Coach } from '../../../models/Coach';
import { Subscription,interval } from 'rxjs';

@Component({
  selector: 'app-chat-client',
  templateUrl: './chat-client.component.html',
  styleUrl: './chat-client.component.css'
})
export class ChatClientComponent implements OnInit {



  coach!:Coach;
  chat!:Chat;
  reply!:string;
  messages:Array<Message>=[];
  message!: string;
  @ViewChild('chatContainer') private chatContainer!: ElementRef;

  constructor(private chatservice:ChatService,private coachservice:CoachService,public authservice:AuthService){ 
  }
  private messageSubscription!: Subscription;

  ngOnInit(): void {
    this.getReply();
    this.setupAutoRefresh()
    
  }

  getReply(){
    this.coachservice.getCoachClient().subscribe({
      next:(data:any)=> {
        this.coach=data
        this.reply=this.coach.email
        this.getMessages(this.coach.email)
      },
      error:err=>console.log(err)
    })
  }

  getMessages(replay:string){
    this.chatservice.getMessages(replay).subscribe({
      next:(data:any)=>{
          this.chat=data;
          this.messages=data.messageDTOS
          this.scrollToBottom();

      },
      error:err=>console.log(err)
    })
  }
  scrollToBottom(): void {
    try {
      setTimeout(() => {
        this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
      }, 0);
    } catch(err) {
      console.error('Scroll to bottom failed', err);
    }
  }
  handlesubmit() {
    this.chatservice.AddMessage(this.reply,this.message).subscribe({
      next:data=>{
          this.getMessages(this.reply);
          this.message="";
      },
      error:err=>console.log(err)
    })
  }
  setupAutoRefresh(): void {
    if (this.messageSubscription) {
      this.messageSubscription.unsubscribe();
    }
    this.messageSubscription = interval(1000).subscribe(() => {
      if (this.reply) {
        this.getMessages(this.reply);
      }
    });
  }


}
