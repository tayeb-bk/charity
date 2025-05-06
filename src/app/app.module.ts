import { NgModule,LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { NavbarComponent } from './navbar/navbar.component'; // Assurez-vous que le chemin est correct
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { AboutComponent } from './about/about.component';
import { BlogComponent } from './blog/blog.component';
import { NgxScannerQrcodeComponent } from 'ngx-scanner-qrcode';
import { GalleryComponent } from './gallery/gallery.component';
import { DonateComponent } from './donate/donate.component';
import { BlogDetailsComponent } from './blog-details/blog-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { OpportunityComponent } from './frontoffice/opportunity/opportunity.component';
import { TrainingComponent } from './frontoffice/training/training.component';
import { ViewOpportunityComponent } from './view-opportunity/view-opportunity.component';
import { EditOpportunityComponent } from './edit-opportunity/edit-opportunity.component';
import { TrainingListComponent } from './training-list/training-list.component';
import { CalendarComponent } from './calendar/calendar.component';
import { TrainingCalenderComponent } from './training-calender/training-calender.component';
import { QrScannerComponent } from './qr-scanner/qr-scanner.component';
import { EditTrainingComponent } from './edit-training/edit-training.component';
import { CalendarTrainingComponent } from './frontoffice/calendriertraining/calendar-training/calendar-training.component';
import localeFr from '@angular/common/locales/fr';
import { registerLocaleData } from '@angular/common';
registerLocaleData(localeFr, 'fr');

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
    DonateComponent,
    BlogDetailsComponent,
    DashboardComponent,
    OpportunityComponent,
    TrainingComponent,
    ViewOpportunityComponent,
    EditOpportunityComponent,
    TrainingListComponent,
    CalendarComponent,
    TrainingCalenderComponent,
    QrScannerComponent,
    EditTrainingComponent,
    CalendarTrainingComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    NgxScannerQrcodeComponent, 
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'fr' },],
  bootstrap: [AppComponent],
})
export class AppModule {}