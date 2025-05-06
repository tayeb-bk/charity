// import { Component } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { CommenterService } from '../commenterService';

// @Component({
//   selector: 'app-commenter',
//   templateUrl: './commenter.component.html',
//   styleUrls: ['./commenter.component.css'],

// })
// export class CommenterComponent {
//   commenterForm: FormGroup;
//   isSubmitting = false;
//   successMessage = '';
//   errorMessage = '';

//   constructor(private fb: FormBuilder, private commenterService: CommenterService) {
//     this.commenterForm = this.fb.group({
//       contenu: ['', [Validators.required, Validators.minLength(3)]],
//       dateCommentaire: [new Date(), Validators.required],
//     });
//   }

//   onSubmit() {
//     if (this.commenterForm.invalid) {
//       return;
//     }

//     this.isSubmitting = true;
//     this.successMessage = '';
//     this.errorMessage = '';

//     this.commenterService.addComment(this.commenterForm.value).subscribe({
//       next: (response: any) => {
//         this.successMessage = 'Commentaire ajouté avec succès !';
//         this.commenterForm.reset();
//         this.commenterForm.patchValue({ dateCommentaire: new Date() });
//       },
//       error: (err: any) => {
//         console.error('Erreur:', err);
//         this.errorMessage = 'Une erreur est survenue lors de l\'ajout.';
//       },
//       complete: () => {
//         this.isSubmitting = false;
//       }
//     });
//   }
// }
