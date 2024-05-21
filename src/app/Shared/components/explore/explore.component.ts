import { Component, HostListener, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Coach } from '../../models/Coach';
import { CoachService } from '../../../core/services/coach/coach.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrl: './explore.component.css'
})
export class ExploreComponent implements OnInit {

  coaches:Coach[] = [];
  keyword:string="";
  currentPage:number=0;
  size:number=6;
  totalElement!:number;
  private isLoading: boolean = false;

 
 
  constructor(private coachservice:CoachService,private router:Router){
this.getCoaches();
  }
  ngOnInit(): void {

    
  }

  getCoaches(): void {
    // Vérifier si une requête est déjà en cours
    if (this.isLoading) {
      return;
    }
  
    this.isLoading = true; // Marquer le chargement comme en cours
    this.coachservice.listCoachs(this.keyword, this.currentPage, this.size)
      .subscribe({
        next: (res: any) => {
          this.coaches = [...this.coaches, ...res.items];
          this.isLoading = false; // Marquer le chargement comme terminé
          this.totalElement=res.totalItems;
          if((this.totalElement/this.size)>this.currentPage){
           
            this.currentPage++;
          }
           // Incrémenter currentPage ici pour la prochaine requête
        }
      });
  }
  handleChangeKeyword() {
   this.coaches=[]
    if (this.isLoading) {
      return;
    }
  
    this.isLoading = true; // Marquer le chargement comme en cours
    this.coachservice.listCoachs(this.keyword, this.currentPage, this.size)
      .subscribe({
        next: (res: any) => {
          this.coaches = [...this.coaches, ...res.items];
          this.isLoading = false; // Marquer le chargement comme terminé
        }
      });
 }
 


  @HostListener('window:scroll', ['$event'])
  onScroll() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
      this.getCoaches();
    }
  }

  handleclick(coach: Coach) {
    this.router.navigateByUrl(`explorer/${coach.id}/${coach.firstName.replace(' ','')}-${coach.lastName.replace(' ','')}`)
    }

}
