import {TestBed} from '@angular/core/testing';

import {ClientElearningService} from './client-elearning.service';

describe('ClientElearningService', () => {
  let service: ClientElearningService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientElearningService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
