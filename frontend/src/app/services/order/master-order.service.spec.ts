import { TestBed } from '@angular/core/testing';

import { MasterOrderService } from './master-order.service';

describe('MasterOrderService', () => {
  let service: MasterOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MasterOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
