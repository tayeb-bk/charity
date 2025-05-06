import { NgModule, APP_INITIALIZER, inject } from '@angular/core';
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
import { BlogSpaceComponent } from './blog-space/blog-space.component';

import {HttpClientModule, provideHttpClient, withInterceptors} from "@angular/common/http";
/*
import { keycloakHttpInterceptor } from "./utils/http/keycloak-http.interceptor";
*/
import { KeycloakService } from "./utils/keycloak/keycloak.service";
import { BlogWebComponent } from './blog-web/blog-web.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AngularMaterialModule} from "./blog-web/AngularMaterialModule";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CreatePostComponent } from './blog-web/pages/create-post/create-post.component';
import { ViewAllComponent } from './blog-web/pages/view-all/view-all.component';
import {keycloakHttpInterceptor} from "./utils/http/keycloak-http.interceptor";
import {initializeKeycloak} from "./app.config";
import { MainComponent } from './pages/main/main.component';
import { ChatListComponent } from './components/chat-list/chat-list.component';

/*export function initializeKeycloak(keycloakService: KeycloakService) {
  return () => keycloakService.init();
}*/

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
    BlogSpaceComponent,
    BlogWebComponent,
    CreatePostComponent,
    ViewAllComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MainComponent

  ],
  providers: [
    provideHttpClient(
      withInterceptors([keycloakHttpInterceptor])
    ),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
