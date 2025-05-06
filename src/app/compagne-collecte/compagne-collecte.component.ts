import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompagneCollecteService } from '../compagne-collecte.service';

@Component({
  selector: 'app-compagne-collecte',
  templateUrl: './compagne-collecte.component.html',
})
export class CompagneCollecteComponent {
  compagneForm: FormGroup;
  isSubmitting = false;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private compagneService: CompagneCollecteService) {
    this.compagneForm = this.fb.group({

      titre: ['', Validators.required],
      description: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      objectif: [0, [Validators.required, Validators.min(1)]],
      moyenPaiement: ['CARTE_BANCAIRE', Validators.required],
      montantCollecte: [0, [Validators.required, Validators.min(1)]],
    });
  }

  onSubmit() {
    if (this.compagneForm.invalid) {
      return;
    }

    this.isSubmitting = true;
    this.successMessage = '';
    this.errorMessage = '';

    this.compagneService.ajouterCompagneCollecte(this.compagneForm.value).subscribe({
      next: (response) => {
        this.successMessage = 'Compagne ajoutée avec succès !',response;
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout:', err);
        this.errorMessage = 'Une erreur est survenue lors de l\'ajout.';
      },
      complete: () => {
        this.isSubmitting = false;
      }
    });
  }
}
