import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../../core/services/client/client.service';
import { Client } from '../../../models/Client';
import { AuthService } from '../../../../core/services/auth/auth.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit{
  
  clients: Array<Client> = [];
  keyword: string = "";
  currentPage: number = 0;
  size: number = 10;
  totalItems: number = 0;
  constructor(private clientservice:ClientService,public authservice:AuthService){

  }

  ngOnInit(): void {

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


}
