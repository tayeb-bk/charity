import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CompagneCollecte {
  id: number;
  titre: string;
  description: string;
  objectif: number;
  montantCollecte: number;
  dateDebut: string;
  dateFin: string;
  moyenPaiement: 'CARTE_BANCAIRE' | 'VIREMENT' | 'ESPECES' | 'PAYPAL';
}

@Injectable({
  providedIn: 'root',
})
export class CompagneCollecteService {
  private apiUrl = 'http://localhost:8090/api/v1';

  constructor(private http: HttpClient) {}

  getAllCompagneCollecte(): Observable<CompagneCollecte[]> {
    return this.http.get<CompagneCollecte[]>(`${this.apiUrl}/getAllCompagneCollecte`);
  }

  getCompagneCollecteById(id: number): Observable<CompagneCollecte> {
    return this.http.get<CompagneCollecte>(`${this.apiUrl}/getCompagneCollecteById${id}`);
  }

  ajouterCompagneCollecte(compagne: CompagneCollecte): Observable<CompagneCollecte> {
    return this.http.post<CompagneCollecte>(`${this.apiUrl}/ajouterCompagneCollecte`, compagne);
  }

  updateCompagneCollecte(id: number, compagne: CompagneCollecte): Observable<CompagneCollecte> {
    return this.http.put<CompagneCollecte>(`${this.apiUrl}/updateCompagneCollecte${id}`, compagne);
  }

  deleteCompagneCollecte(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteCompagneCollecte${id}`);
  }
}
