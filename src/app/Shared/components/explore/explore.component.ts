import { Component, HostListener, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Coach } from '../../models/Coach';
import { CoachService } from '../../../core/services/coach/coach.service';

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
  private isLoading: boolean = false;

 
 
  constructor(private coachservice:CoachService){
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
      .pipe(
        map((data: any) => data.items)
      )
      .subscribe({
        next: (res: Coach[]) => {
          this.coaches = [...this.coaches, ...res];
          this.isLoading = false; // Marquer le chargement comme terminé
          this.currentPage++; // Incrémenter currentPage ici pour la prochaine requête
        }
      });
  }
  @HostListener('window:scroll', ['$event'])
  onScroll() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
      this.getCoaches();
    }
  }

}
