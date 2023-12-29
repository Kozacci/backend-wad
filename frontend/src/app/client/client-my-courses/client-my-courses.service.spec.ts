import {TestBed} from '@angular/core/testing';

import {ClientMyCoursesService} from './client-my-courses.service';

describe('ClientMyCoursesService', () => {
  let service: ClientMyCoursesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientMyCoursesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
