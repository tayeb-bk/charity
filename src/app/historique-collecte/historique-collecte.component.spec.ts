import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoriqueCollecteComponent } from './historique-collecte.component';

describe('HistoriqueCollecteComponent', () => {
  let component: HistoriqueCollecteComponent;
  let fixture: ComponentFixture<HistoriqueCollecteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HistoriqueCollecteComponent]
    });
    fixture = TestBed.createComponent(HistoriqueCollecteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
