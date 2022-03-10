import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AverageProfitComponent } from './average-profit.component';

describe('AverageProfitComponent', () => {
  let component: AverageProfitComponent;
  let fixture: ComponentFixture<AverageProfitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AverageProfitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AverageProfitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
