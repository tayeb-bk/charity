import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Donation {
  id?: number;
  nom: string;
  prenom: string;
  adresse: string;
  numero: string;
  description: string;
  moyenPaiement: 'CARTE_BANCAIRE' | 'VIREMENT' | 'ESPECES' | 'PAYPAL';
  typeDonation: 'MATERIEL' | 'MONETAIRE' | 'SERVICES';
  etat: 'NEUF' | 'BON_ETAT' | 'USE';
  amount?: number;
}

@Injectable({
  providedIn: 'root',
})
export class DonationService {
  private apiUrl = 'http://localhost:8090/api/v1';

  constructor(private http: HttpClient) {}

  getAllDonations(): Observable<Donation[]> {
    return this.http.get<Donation[]>(`${this.apiUrl}/getAllDonation`);
  }

  getDonationById(id: number): Observable<Donation> {
    return this.http.get<Donation>(`${this.apiUrl}/getDonationById${id}`);
  }
 addDonation(donation: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouterDonation`, donation);
  }

  updateDonation(id: number, donation: Donation): Observable<Donation> {
    return this.http.put<Donation>(`${this.apiUrl}/updateDonation${id}`, donation);
  }

  deleteDonation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteDonation${id}`);
  }
}
