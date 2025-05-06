import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlogWebComponent } from './blog-web.component';

describe('BlogWebComponent', () => {
  let component: BlogWebComponent;
  let fixture: ComponentFixture<BlogWebComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BlogWebComponent]
    });
    fixture = TestBed.createComponent(BlogWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
