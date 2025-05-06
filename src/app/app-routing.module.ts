import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { AboutComponent } from './about/about.component';
import { BlogComponent } from './blog/blog.component';
import { BlogDetailsComponent } from './blog-details/blog-details.component';
import { DonateComponent } from './donate/donate.component';
import { GalleryComponent } from './gallery/gallery.component';
import {BlogSpaceComponent} from "./blog-space/blog-space.component";
import {BlogWebComponent} from "./blog-web/blog-web.component";
import {CreatePostComponent} from "./blog-web/pages/create-post/create-post.component";
import {ViewAllComponent} from "./blog-web/pages/view-all/view-all.component";
import {MainComponent} from "./pages/main/main.component";


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'how-it-works', component: HowItWorksComponent },
  { path: 'about', component: AboutComponent},
  { path: 'blog', component: BlogComponent},
  { path: 'blog_details', component: BlogDetailsComponent},
  { path: 'donate', component: DonateComponent},
  { path: 'gallery', component: GalleryComponent},
  { path: 'blog-sapce', component: BlogSpaceComponent},
  { path: 'blog-web', component: BlogWebComponent},
  { path: 'create-post', component: CreatePostComponent},
  { path: 'view-all', component: ViewAllComponent},
  { path: 'posts', redirectTo: 'view-all', pathMatch: 'full' }







];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
