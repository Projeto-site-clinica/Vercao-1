import { TestBed } from '@angular/core/testing';

import { DoutorHorarioService } from './doutor-horario.service';

describe('DoutorHorarioService', () => {
  let service: DoutorHorarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoutorHorarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
