import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SweepOrderFormComponent } from './sweep-order-form.component';

describe('SweepOrderFormComponent', () => {
  let component: SweepOrderFormComponent;
  let fixture: ComponentFixture<SweepOrderFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SweepOrderFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SweepOrderFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
