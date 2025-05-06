import { DonationService } from './../service/donation.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-historique-dons',
  templateUrl: './historique-dons.component.html',
  styleUrls: ['./historique-dons.component.css']
})
export class HistoriqueDonsComponent implements OnInit {
  donations: any[] = [];
  filteredDonations: any[] = [];
  isLoading = false;
  errorMessage: string | null = null;
  searchType: string = '';

  constructor(private donationService: DonationService) {}

  ngOnInit(): void {
    this.getDonations();
  }

  getDonations(): void {
    this.isLoading = true;
    this.donationService.getAllDonations().subscribe({
      next: (data) => {
        this.donations = data;
        this.filteredDonations = data; // Affiche tous les dons par défaut
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des dons.';
        console.error(err);
        this.isLoading = false;
      }
    });
  }

  filterDonations(): void {
    if (!this.searchType) {
      this.filteredDonations = this.donations;
    } else {
      this.filteredDonations = this.donations.filter(donation =>
        donation.typeDonation.toLowerCase() === this.searchType.toLowerCase()
      );
    }
  }

  isMonetaryDonation(donation: any): boolean {
    return donation.typeDonation === 'MONETAIRE';
  }

  isMaterialDonation(donation: any): boolean {
    return donation.typeDonation === 'MATERIEL';
  }
  deleteDonation(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer ce don ?')) {
      this.donationService.deleteDonation(id).subscribe({
        next: () => {
          // Met à jour les listes après suppression
          this.donations = this.donations.filter(d => d.id !== id);
          this.filteredDonations = this.filteredDonations.filter(d => d.id !== id);
        },
        error: (err) => {
          console.error('Erreur lors de la suppression du don', err);
          alert('Erreur lors de la suppression du don');
        }
      });
    }
  }
}
