import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientCourseDetailsSailorComponent } from './client-course-details-sailor.component';

describe('ClientCourseDetailsSailorComponent', () => {
  let component: ClientCourseDetailsSailorComponent;
  let fixture: ComponentFixture<ClientCourseDetailsSailorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCourseDetailsSailorComponent]
    });
    fixture = TestBed.createComponent(ClientCourseDetailsSailorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
