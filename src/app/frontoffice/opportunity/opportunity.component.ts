import { Component, OnInit } from '@angular/core';
import { Opportunity } from 'src/app/models/opportunity.model';
import { OpportunityServiceService } from 'src/app/serviceOpportunity/opportunity-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-opportunity',
  templateUrl: './opportunity.component.html',
  styleUrls: ['./opportunity.component.css']
})
export class OpportunityComponent implements OnInit {
  successMessage: string = '';
  errorMessage: string = '';
  qrData: string = '';  // Variable pour stocker les données QR Code

  opportunity: Opportunity = {
    title: '',
    description: '',
    category: 'EMPLOI',
    location: '',
    postedBy: {
      id: 0,
      email: '',
      first_name: '',
      last_name: '',
      password: '',
      password_reset_token: '',
      role: '',
      enabled: 0,
      phonenumber: ''
    },
    datePosted: '',
    deadline: '',
    type: 'FULL_TIME',
    aiScore: 0
  };

  constructor(private opportunityService: OpportunityServiceService, private router: Router) {}

  ngOnInit(): void {
    const today = new Date().toISOString().split('T')[0];
    this.opportunity.datePosted = today;
    this.opportunity.deadline = today;
  }

  submitForm(): void {
    if (!this.validateForm()) {
      this.errorMessage = 'Veuillez remplir tous les champs obligatoires.';
      return;
    }

    this.opportunityService.createOpportunity(this.opportunity).subscribe({
      next: (response) => {
        console.log('Opportunité créée avec succès', response);
        this.successMessage = 'L\'opportunité a été créée avec succès ! 🎉';
        this.errorMessage = '';

        // 🆕 Générer QR Code data
        this.qrData = JSON.stringify({
          title: this.opportunity.title,
          description: this.opportunity.description,
          category: this.opportunity.category,
          location: this.opportunity.location,
          deadline: this.opportunity.deadline,
          type: this.opportunity.type
        });

        this.resetForm();
      },
      error: (error) => {
        console.error('Erreur lors de la création', error);
        this.successMessage = '';
        this.errorMessage = typeof error === 'string' ? error : 'Erreur lors de la création de l\'opportunité. Veuillez réessayer.';
      }
    });
  }

  resetForm() {
    const today = new Date().toISOString().split('T')[0];
    this.opportunity = {
      title: '',
      description: '',
      category: 'EMPLOI',
      location: '',
      postedBy: {
        id: 0,
        email: '',
        first_name: '',
        last_name: '',
        password: '',
        password_reset_token: '',
        role: '',
        enabled: 0,
        phonenumber: ''
      },
      datePosted: today,
      deadline: today,
      type: 'FULL_TIME',
      aiScore: 0
    };
  }

  validateForm(): boolean {
    return (
      this.opportunity.title.trim() !== '' &&
      this.opportunity.description.trim() !== '' &&
      this.opportunity.deadline !== ''
    );
  }
}
