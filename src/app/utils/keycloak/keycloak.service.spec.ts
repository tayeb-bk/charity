import { TestBed } from '@angular/core/testing';
import { KeycloakService } from './keycloak.service';

describe('KeycloakService', () => {  // 🛠️ Correction ici
  let service: KeycloakService;      // 🛠️ Correction ici

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KeycloakService);  // 🛠️ Correction ici
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
