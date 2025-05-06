import { Component, OnInit } from '@angular/core';
import { CompagneCollecteService } from '../compagne-collecte.service';
import { CompagneCollecte } from '../services/models/compagne-collecte';

@Component({
  selector: 'app-historique-collecte',
  templateUrl: './historique-collecte.component.html',
  styleUrls: ['./historique-collecte.component.css']
})


export class HistoriqueCollecteComponent implements OnInit {
  compagnesCollecte: CompagneCollecte[] = [];
  isLoading = true;
  errorMessage: string | null = null;

  constructor(private collecteService: CompagneCollecteService) {}

  ngOnInit(): void {
    this.fetchCompagnesCollecte();
  }

  fetchCompagnesCollecte(): void {
    this.collecteService.getAllCompagneCollecte().subscribe({
      next: (data) => {
        this.compagnesCollecte = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des campagnes de collecte.';
        console.error('Erreur:', err);
        this.isLoading = false;
      }
    });
  }
  deleteCompagneCollecte(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette campagne de collecte ?')) {
      this.collecteService.deleteCompagneCollecte(id).subscribe({
        next: () => {
          // Met à jour la liste après suppression
          this.compagnesCollecte = this.compagnesCollecte.filter(c => c.id !== id);
        },
        error: (err) => {
          console.error('Erreur lors de la suppression de la campagne', err);
          alert('Erreur lors de la suppression de la campagne');
        }
      });
    }
  }
  



}

