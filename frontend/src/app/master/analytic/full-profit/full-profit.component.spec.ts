import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FullProfitComponent } from './full-profit.component';

describe('FullProfitComponent', () => {
  let component: FullProfitComponent;
  let fixture: ComponentFixture<FullProfitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FullProfitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FullProfitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
