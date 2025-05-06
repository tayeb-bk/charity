import { TestBed } from '@angular/core/testing';

import { CompagneCollecteService } from './compagne-collecte.service';

describe('CompagneCollecteService', () => {
  let service: CompagneCollecteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompagneCollecteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
