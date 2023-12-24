import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientCourseDetailsComponent} from './client-course-details.component';

describe('ClientCourseDetailsComponent', () => {
  let component: ClientCourseDetailsComponent;
  let fixture: ComponentFixture<ClientCourseDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCourseDetailsComponent]
    });
    fixture = TestBed.createComponent(ClientCourseDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
