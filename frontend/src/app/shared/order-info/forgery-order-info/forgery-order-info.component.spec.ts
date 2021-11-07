import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgeryOrderInfoComponent } from './forgery-order-info.component';

describe('ForgeryOrderInfoComponent', () => {
  let component: ForgeryOrderInfoComponent;
  let fixture: ComponentFixture<ForgeryOrderInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForgeryOrderInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForgeryOrderInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
