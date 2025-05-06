import { Component, OnInit } from '@angular/core';
import { PostControllerService } from '../../../services/services'; // Assurez-vous que le service est correctement importé
import { Post } from '../../../services/models'; // Assurez-vous que Post est bien défini dans votre modèle

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.css']
})
export class ViewAllComponent implements OnInit {
  posts: Post[] = [];  // Tableau pour stocker la liste des posts
  isLoading: boolean = false;  // Indicateur de chargement
  errorMessage: string = '';  // Message d'erreur, si nécessaire

  constructor(private postService: PostControllerService) {}

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(): void {
    this.isLoading = true;
    this.errorMessage = '';

    // Utilisation de `HttpClient` pour demander les posts en tant que JSON
    this.postService.getAllPosts().subscribe({
      next: (posts: Post[]) => {
        this.isLoading = false;
        this.posts = posts;
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = 'Impossible de charger les posts. Essayez à nouveau.';
        console.error('Erreur lors du chargement des posts:', err);
      }
    });
  }
}
