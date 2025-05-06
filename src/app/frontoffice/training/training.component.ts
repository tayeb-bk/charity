import { Component } from '@angular/core';
import { TrainingServiceService } from 'src/app/serviceTraining/training-service.service';
import { Training } from 'src/app/models/training.model';

@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css']
})
export class TrainingComponent {
  training: Training = {
    title: '',
    description: '',
    category: 'FORMATION',
    location: '',
    trainer: '',
    startDate: '',
    endDate: '',
    type: 'FULL_TIME'
  };

  constructor(private trainingService: TrainingServiceService) {}

  addTraining() {
    if (!this.validateForm()) {
      alert('Veuillez remplir tous les champs obligatoires.');
      return;
    }

    console.log("Données envoyées :", this.training);

    this.trainingService.createTraining(this.training).subscribe({
      next: (response) => {
        console.log('Formation créée avec succès', response);
        alert('Formation ajoutée avec succès !');
        this.resetForm();
      },
      error: (error) => {
        console.error('Erreur lors de la création', error);
        alert('Erreur lors de la création de la formation.');
      }
    });
  }

  resetForm() {
    this.training = {
      title: '',
      description: '',
      category: 'FORMATION',
      location: '',
      trainer: '',
      startDate: '',
      endDate: '',
      type: 'FULL_TIME'
    };
  }

  validateForm(): boolean {
    return (
      this.training.title.trim() !== '' &&
      this.training.description.trim() !== '' &&
      this.training.startDate !== '' &&
      this.training.endDate !== ''
    );
  }
}
