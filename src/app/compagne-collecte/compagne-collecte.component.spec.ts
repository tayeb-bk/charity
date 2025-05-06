import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompagneCollecteComponent } from './compagne-collecte.component';

describe('CompagneCollecteComponent', () => {
  let component: CompagneCollecteComponent;
  let fixture: ComponentFixture<CompagneCollecteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompagneCollecteComponent]
    });
    fixture = TestBed.createComponent(CompagneCollecteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
