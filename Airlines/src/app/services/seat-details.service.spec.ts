import { TestBed } from '@angular/core/testing';

import { SeatDetailsService } from './seat-details.service';

describe('SeatDetailsService', () => {
  let service: SeatDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeatDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
