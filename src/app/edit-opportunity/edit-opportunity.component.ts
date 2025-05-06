import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OpportunityServiceService } from 'src/app/serviceOpportunity/opportunity-service.service';
import { Opportunity } from 'src/app/models/opportunity.model';
import { Categorie, Type } from 'src/app/models/enums';

@Component({
  selector: 'app-edit-opportunity',
  templateUrl: './edit-opportunity.component.html',
  styleUrls: ['./edit-opportunity.component.css']
})
export class EditOpportunityComponent implements OnInit {
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
      enabled: 1,
      phonenumber: ''
    },
    datePosted: '',
    deadline: '',
    type: 'FULL_TIME',
    aiScore: 0
  };

  errorMessage: string = '';
  successMessage: string = '';
  categories = Object.values(Categorie);
  types = Object.values(Type);

  constructor(
    private opportunityService: OpportunityServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.getOpportunity(id);
  }

  getOpportunity(id: number): void {
    this.opportunityService.getOpportunityById(id).subscribe(
      (data: Opportunity) => {
        this.opportunity = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération de l\'opportunité:', error);
        this.errorMessage = "Une erreur s'est produite lors du chargement des données de l'opportunité.";
      }
    );
  }

  updateOpportunity(): void {
    if (this.opportunity.id) {
      // Créez un objet avec uniquement les champs modifiables
      const opportunityToUpdate = {
        title: this.opportunity.title,
        description: this.opportunity.description,
        category: this.opportunity.category,
        location: this.opportunity.location,
        
        type: this.opportunity.type
      };
  
      this.opportunityService.updateOpportunity(this.opportunity.id, opportunityToUpdate).subscribe(
        () => {
          this.successMessage = 'Opportunité modifiée avec succès !';
          this.errorMessage = '';
          setTimeout(() => {
            this.router.navigate(['/viewopportunity']);
          }, 1500);
        },
        (error) => {
          console.error('Erreur:', error);
          this.errorMessage = error.error?.message || "Erreur lors de la mise à jour";
          this.successMessage = '';
        }
      );
    }
  }
  
  // Ajoutez cette méthode pour formater les dates
  
}