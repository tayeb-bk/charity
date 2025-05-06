import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoriqueDonsComponent } from './historique-dons.component';

describe('HistoriqueDonsComponent', () => {
  let component: HistoriqueDonsComponent;
  let fixture: ComponentFixture<HistoriqueDonsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HistoriqueDonsComponent]
    });
    fixture = TestBed.createComponent(HistoriqueDonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
