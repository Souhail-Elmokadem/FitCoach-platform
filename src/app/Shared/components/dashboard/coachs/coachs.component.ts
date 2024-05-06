import { Component, Input, OnInit } from '@angular/core';
import { ClientService } from '../../../../core/services/client/client.service';
import { Client } from '../../../models/Client';

@Component({
  selector: 'app-coachs',
  templateUrl: './coachs.component.html',
  styleUrls: ['./coachs.component.css'],
})
export class CoachsComponent implements OnInit {

  clients: Array<Client> = [];
  keyword: string = "";
  currentPage: number = 0;
  size: number = 6;
  totalItems: number = 0;

  constructor(private clientservice: ClientService) {
    
  }

  ngOnInit(): void {
    this.getClients();
  }

  getClients(): void {
    this.clientservice.getClients(this.keyword, this.currentPage, this.size).subscribe({
      next: (data: any) => {
        this.clients = data.items;
        this.totalItems = data.totalItems;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  handleNext() {
    let pages = this.totalItems / this.size;
    if (this.currentPage < pages) {
      this.currentPage++;
      this.getClients();
    }
  }

  handlePrev() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getClients();
    }
  }
   handlesearch(newKeyword: string) {
    this.keyword = newKeyword;
    console.log(this.keyword);
    this.getClients(); // Update the clients based on the new keyword
  }

}
