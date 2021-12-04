import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PickpocketingOrderInfoComponent } from './pickpocketing-order-info.component';

describe('PickpocketingOrderInfoComponent', () => {
  let component: PickpocketingOrderInfoComponent;
  let fixture: ComponentFixture<PickpocketingOrderInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PickpocketingOrderInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PickpocketingOrderInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
