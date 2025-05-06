import { Component, OnInit } from '@angular/core';
import { TrainingServiceService } from 'src/app/serviceTraining/training-service.service';
import { Router } from '@angular/router';
import { Training } from 'src/app/models/training.model';

@Component({
  selector: 'app-training-list',
  templateUrl: './training-list.component.html',
  styleUrls: ['./training-list.component.css']
})
export class TrainingListComponent implements OnInit {
  trainings: Training[] = [];

  constructor(
    private trainingService: TrainingServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getTrainings();
  }

  getTrainings(): void {
    this.trainingService.getAllTrainings().subscribe({
      next: (data) => {
        this.trainings = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des formations', err);
      }
    });
  }

  deleteTraining(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette formation ?')) {
      this.trainingService.deleteTraining(id).subscribe({
        next: () => {
          this.trainings = this.trainings.filter(t => t.id !== id);
          alert('Formation supprimée avec succès !');
        },
        error: (err) => {
          console.error('Erreur lors de la suppression', err);
          alert('Erreur lors de la suppression de la formation.');
        }
      });
    }
  }

  editTraining(id: number): void {
    this.router.navigate(['/edit-training', id]);
  }
}
