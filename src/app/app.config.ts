import { ApplicationConfig, inject, provideZoneChangeDetection, APP_INITIALIZER } from '@angular/core';
import { provideRouter } from "@angular/router";
import { appRoutes } from './app.routes';
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import { KeycloakService } from "./utils/keycloak/keycloak.service";
import {keycloakHttpInterceptor} from "./utils/http/keycloak-http.interceptor";
/*
import { keycloakHttpInterceptor } from './utils/http/keycloak-http.interceptor';
*/


export function initializeKeycloak(keycloakService: KeycloakService) {

  return () => keycloakService.init();

}

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(appRoutes),

    provideHttpClient(
      withInterceptors([keycloakHttpInterceptor])
    ),

    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService], // Injection du service Keycloak
      multi: true
    }
  ]
};
