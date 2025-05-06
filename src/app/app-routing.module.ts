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
import { OpportunityComponent } from './frontoffice/opportunity/opportunity.component';
import { TrainingComponent } from './frontoffice/training/training.component';
import { ViewOpportunityComponent } from './view-opportunity/view-opportunity.component';
import { EditOpportunityComponent } from './edit-opportunity/edit-opportunity.component';
import { TrainingListComponent } from './training-list/training-list.component';
import { CalendarComponent } from './calendar/calendar.component';
import { CalendarTrainingComponent } from './frontoffice/calendriertraining/calendar-training/calendar-training.component';
const routes: Routes = [
  { path: '', component: HomeComponent }, 
  { path: 'contact', component: ContactComponent },
  { path: 'caleNDAR', component: CalendarComponent },
  { path: 'caleNDARtrain', component: CalendarTrainingComponent },
  { path: 'how-it-works', component: HowItWorksComponent },
  { path: 'about', component: AboutComponent},
  { path: 'blog', component: BlogComponent},
  { path: 'blog_details', component: BlogDetailsComponent},
  { path: 'donate', component: DonateComponent},
  { path: 'opp', component: OpportunityComponent},

  { path: 'gallery', component: GalleryComponent},
  { path: 'edit-opportunity/:id', component: EditOpportunityComponent },
  { path: 'opportunity', component: OpportunityComponent},
  { path: 'viewopportunity', component: ViewOpportunityComponent},
  { path: '', redirectTo: '/viewopportunity', pathMatch: 'full' },
  { path: 'training', component: TrainingComponent},
  { path: 'viewtraining', component: TrainingListComponent},
  { path: 'calendar', component: CalendarComponent} ,
  { path: 'calendar', component: CalendarComponent} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
