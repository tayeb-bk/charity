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
import { CompagneCollecteComponent } from './compagne-collecte/compagne-collecte.component';
import { HistoriqueDonsComponent } from './historique-dons/historique-dons.component';
import { HistoriqueCollecteComponent } from './historique-collecte/historique-collecte.component';
// import { CommenterComponent } from './commenter/commenter.component';


const routes: Routes = [
  { path: '', component: HomeComponent }, 
  { path: 'contact', component: ContactComponent },
  { path: 'how-it-works', component: HowItWorksComponent },
  { path: 'about', component: AboutComponent},
  { path: 'blog', component: BlogComponent},
  { path: 'blog_details', component: BlogDetailsComponent},
  { path: 'donate', component: DonateComponent},
  { path: 'gallery', component: GalleryComponent},
  { path: 'compagne_collecte', component: CompagneCollecteComponent},
  { path: 'historique-dons', component: HistoriqueDonsComponent },
  { path: 'historique-collecte', component: HistoriqueCollecteComponent }
  // { path: 'commenter', component: CommenterComponent }


 
];

@NgModule({
  imports: [RouterModule.forRoot(routes, )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
