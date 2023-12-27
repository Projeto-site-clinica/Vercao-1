import { TestBed } from '@angular/core/testing';

import { DoutorService } from './doutor.service';

describe('DoutorService', () => {
  let service: DoutorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoutorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
