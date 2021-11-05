import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgeryOrderFormComponent } from './forgery-order-form.component';

describe('ForgeryOrderFormComponent', () => {
  let component: ForgeryOrderFormComponent;
  let fixture: ComponentFixture<ForgeryOrderFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForgeryOrderFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForgeryOrderFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
