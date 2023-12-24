import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientCourseDetailsSteersmanComponent} from './client-course-details-steersman.component';

describe('ClientCourseDetailsSteersmanComponent', () => {
  let component: ClientCourseDetailsSteersmanComponent;
  let fixture: ComponentFixture<ClientCourseDetailsSteersmanComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCourseDetailsSteersmanComponent]
    });
    fixture = TestBed.createComponent(ClientCourseDetailsSteersmanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
