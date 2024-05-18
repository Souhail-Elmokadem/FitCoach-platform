import { AfterViewChecked, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ClientService } from '../../../../core/services/client/client.service';
import { Client } from '../../../models/Client';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { ChatService } from '../../../../core/services/chat/chat.service';
import { Message } from '../../../models/Message';
import { Chat } from '../../../models/Chat';
import { Subscription,interval } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit,AfterViewChecked{


  
  clients: Array<Client> = [];
  keyword: string = "";
  currentPage: number = 0;
  size: number = 10;
  totalItems: number = 0;
  chat!:Chat;
  reply!:string;
  messages:Array<Message>=[];
  message!: string;
  private messageSubscription!: Subscription;

  @ViewChild('chatContainer') private chatContainer!: ElementRef;
  constructor(private clientservice:ClientService,public authservice:AuthService,private chatservice:ChatService){

  }
  ngAfterViewChecked(): void {
    
  }

  ngOnInit(): void {
      this.getClients();
      this.setupAutoRefresh()
  }

  getClients(): void {
    this.clientservice.listClientCoach(this.keyword, this.currentPage, this.size).subscribe({
      next: (data: any) => {
        this.clients = data.items;
        this.totalItems = data.totalItems;
      },
      error: (err) => {
        console.log(err);
      }
    });
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

  handleClick(cl:Client) {
    this.getMessages(cl.email);
    this.reply=cl.email;
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