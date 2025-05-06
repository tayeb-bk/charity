import { Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";

export const appRoutes: Routes = [
  { path: '/home', loadComponent: () => import('./home/home.component').then(m => m.HomeComponent) },
  { path: 'dashboard', loadComponent: () => import('./dashboard/dashboard.component').then(m => m.DashboardComponent) },
  { path: 'main', component: MainComponent}

];
