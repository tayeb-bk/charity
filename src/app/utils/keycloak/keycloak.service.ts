import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

 // @ts-ignore
  private _keycloak: Keycloak | undefined;

  constructor() {
    // @ts-ignore
    this._keycloak = new Keycloak({
      url: 'http://localhost:8080',
      realm: 'charity',
      clientId: 'tayeb-rest-api'
    });
  }

  // @ts-ignore
  get keycloak(): Keycloak {
    if (!this._keycloak) {
      // @ts-ignore
      this._keycloak = new Keycloak({
        url: 'http://localhost:8080',
        realm: 'charity',
        clientId: 'tayeb-rest-api',
      });
    }
    return this._keycloak;
  }

  async init()  {

      const authenticated = await this.keycloak.init({
        onLoad: 'login-required'
      });
  }
  async login(){
    await this.keycloak.login();
  }

  get userId(){
    return this.keycloak?.tokenParsed?.sub as string;
  }

  get isTokenValid(){
    return !this.keycloak.isTokenExpired();
  }

  get fullName(): string {
    return this.keycloak.tokenParsed?.['name'] as string;
  }

  logout(){
    return this.keycloak.logout({redirectUri: 'http://localhost:4200'});
  }

  accountManagement(){
    return this.keycloak.accountManagement();
  }



}
