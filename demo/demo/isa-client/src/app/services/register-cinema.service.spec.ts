import { TestBed, inject } from '@angular/core/testing';

import { RegisterCinemaService } from './register-cinema.service';

describe('RegisterCinemaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RegisterCinemaService]
    });
  });

  it('should be created', inject([RegisterCinemaService], (service: RegisterCinemaService) => {
    expect(service).toBeTruthy();
  }));
});
