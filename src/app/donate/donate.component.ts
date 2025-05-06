import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DonationService } from './../service/donation.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-donate',
  // standalone: true,
  templateUrl: './donate.component.html',
  styleUrls: ['./donate.component.css']
  // imports: [ReactiveFormsModule, CommonModule],
})
export class DonateComponent {

  donationForm: FormGroup;
  showPaymentMethod = false; 
  showAmountSection = false;
  showEtatSection = false;

  constructor(private fb: FormBuilder, private donationService: DonationService) {
    this.donationForm = this.fb.group({
      nom: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      prenom: ['', Validators.required ],
      adresse: ['', Validators.required ],
      numero: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')] ],
      description: ['', Validators.required ],
      moyenPaiement: ['', Validators.required],
      typeDonation: ['', Validators.required ],
      amount: [0,[Validators.required, Validators.min(0)]],
      etat: ['', Validators.required],
    });
  }

  onSubmit() {
    const donationData = {
      nom: this.donationForm.get('nom')?.value,
      prenom: this.donationForm.get('prenom')?.value,
      adresse: this.donationForm.get('adresse')?.value,
      numero: this.donationForm.get('numero')?.value,
      description: this.donationForm.get('description')?.value,
      amount: this.donationForm.get('amount')?.value,
      typeDonation: this.donationForm.get('typeDonation')?.value,
      moyenPaiement: this.donationForm.get('moyenPaiement')?.value|| null,
      etat: this.donationForm.get('etat')?.value || null, 
    };

    this.donationService.addDonation(donationData).subscribe({
      next: (response) => {
        console.log('Donation added successfully', response);
      },
      error: (error) => {
        console.error('Error adding donation', error);
      }
    });
  }

  onTypeChange() {
    const typeDonation = this.donationForm.get('typeDonation')?.value;

    this.showPaymentMethod = typeDonation === 'MONETAIRE';
    this.showAmountSection = typeDonation === 'MONETAIRE';
    this.showEtatSection = typeDonation === 'MATERIEL';
  }
   
}