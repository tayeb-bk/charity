export interface Opportunity {
    id?: number;
    title: string;
    description: string;
    category: 'EMPLOI' | 'FORMATION' | 'STAGE' | 'BÉNÉVOLAT';
    location: string;
    postedBy: {
      id: number;
      
      email: string;
      first_name: string;   // Ajout de first_name
      last_name: string;    // Ajout de last_name
      password: string;     // Ajout de password
      password_reset_token: string;  // Ajout de password_reset_token
      role: string;         // Ajout du rôle
      enabled: number;      // Ajout de enabled
      phonenumber: string;  // Ajout du phonenumber
    };
    datePosted: string;
    deadline: string;
    type: 'FULL_TIME' | 'PART_TIME' | 'REMOTE' | 'HYBRID';
    aiScore: number;

  }
  