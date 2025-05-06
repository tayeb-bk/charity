export interface Training {
    id?: number; // Optionnel pour la création
    title: string;
    description: string;
    category: 'EMPLOI' | 'FORMATION' | 'STAGE' | 'BÉNÉVOLAT'; // Adapter si nécessaire
    location: string;
    trainer: string; // Exemple de champ
    startDate: string;
    endDate: string;
    type: 'FULL_TIME' | 'PART_TIME' | 'REMOTE' | 'HYBRID'; // Adapter si nécessaire
  }
  