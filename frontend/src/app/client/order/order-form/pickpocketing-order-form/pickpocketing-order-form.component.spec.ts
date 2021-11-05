import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PickpocketingOrderFormComponent } from './pickpocketing-order-form.component';

describe('PickpocketingOrderFormComponent', () => {
  let component: PickpocketingOrderFormComponent;
  let fixture: ComponentFixture<PickpocketingOrderFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PickpocketingOrderFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PickpocketingOrderFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
