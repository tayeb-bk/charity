import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { AboutComponent } from './about/about.component';
import { BlogComponent } from './blog/blog.component';
import { GalleryComponent } from './gallery/gallery.component';
import { DonateComponent } from './donate/donate.component';
import { BlogDetailsComponent } from './blog-details/blog-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HistoriqueDonsComponent } from './historique-dons/historique-dons.component';
import { CompagneCollecteComponent } from './compagne-collecte/compagne-collecte.component';
import { CommonModule } from '@angular/common';
import { HistoriqueCollecteComponent } from './historique-collecte/historique-collecte.component';
// import { CommenterComponent } from './commenter/commenter.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ContactComponent,
    NavbarComponent,
    HowItWorksComponent,
    AboutComponent,
    BlogComponent,
    GalleryComponent,
    BlogDetailsComponent,
    DashboardComponent,
    CompagneCollecteComponent,
    HistoriqueDonsComponent,
    DonateComponent,
    HistoriqueCollecteComponent
    // CommenterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
