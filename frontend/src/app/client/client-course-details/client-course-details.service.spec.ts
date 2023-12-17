import { TestBed } from '@angular/core/testing';

import { ClientCourseDetailsService } from './client-course-details.service';

describe('ClientCourseDetailsService', () => {
  let service: ClientCourseDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientCourseDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
