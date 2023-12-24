import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientCourseDetailsYachtComponent} from './client-course-details-yacht.component';

describe('ClientCourseDetailsYachtComponent', () => {
  let component: ClientCourseDetailsYachtComponent;
  let fixture: ComponentFixture<ClientCourseDetailsYachtComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCourseDetailsYachtComponent]
    });
    fixture = TestBed.createComponent(ClientCourseDetailsYachtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
