import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostControllerService } from './../../../services/services';
import { Post } from './../../../services/models';
import { CreatePost$Params } from './../../../services/fn/post-controller/create-post';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent {
  postForm: FormGroup;
  isSubmitting = false;
  errorMessage = '';
  tags: string[] = [];

  constructor(
    private fb: FormBuilder,
    private postService: PostControllerService,
    private router: Router
  ) {
    this.postForm = this.fb.group({
      name: ['', Validators.required],
      content: ['', Validators.required],
      img: [''],
      tags: [''],
      postedBy: ['', Validators.required]  // <-- Added postedBy field
    });
  }

  /**
   * Add a new tag
   */
  add(event: any): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.tags.push(value.trim());
    }

    // Clear the input value
    if (input) {
      input.value = '';
    }
  }

  /**
   * Remove a tag
   */
  remove(tag: string): void {
    const index = this.tags.indexOf(tag);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  /**
   * Create a new post
   */
  createPost(): void {
    if (this.postForm.invalid) {
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';

    const formValues = this.postForm.value;
    const newPost: Post = {
      name: formValues.name,
      content: formValues.content,
      img: formValues.img,
      tags: this.tags,  // Using the tags array instead of splitting by comma
      postedBy: formValues.postedBy
    };

    // Wrap the newPost inside the body property
    const newPostParams: CreatePost$Params = {
      body: newPost
    };

    this.postService.createPost(newPostParams).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.router.navigate(['/posts']);
      },
      error: (err) => {
        this.isSubmitting = false;
        this.errorMessage = 'Failed to create post. Please try again.';
        console.error('Error while creating post:', err);
      }
    });
  }
}
