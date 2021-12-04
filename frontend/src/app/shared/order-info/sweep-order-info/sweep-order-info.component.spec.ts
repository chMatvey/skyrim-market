import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SweepOrderInfoComponent } from './sweep-order-info.component';

describe('SweepOrderInfoComponent', () => {
  let component: SweepOrderInfoComponent;
  let fixture: ComponentFixture<SweepOrderInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SweepOrderInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SweepOrderInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
