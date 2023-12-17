import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientCourseDetailsInternshipComponent } from './client-course-details-internship.component';

describe('ClientCourseDetailsInternshipComponent', () => {
  let component: ClientCourseDetailsInternshipComponent;
  let fixture: ComponentFixture<ClientCourseDetailsInternshipComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCourseDetailsInternshipComponent]
    });
    fixture = TestBed.createComponent(ClientCourseDetailsInternshipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
