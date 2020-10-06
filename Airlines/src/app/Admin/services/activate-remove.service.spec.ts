import { TestBed } from '@angular/core/testing';

import { ActivateRemoveService } from './activate-remove.service';

describe('ActivateRemoveService', () => {
  let service: ActivateRemoveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActivateRemoveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
