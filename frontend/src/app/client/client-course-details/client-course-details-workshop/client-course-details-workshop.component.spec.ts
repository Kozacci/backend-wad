import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientCourseDetailsWorkshopComponent } from './client-course-details-workshop.component';

describe('ClientCourseDetailsWorkshopComponent', () => {
  let component: ClientCourseDetailsWorkshopComponent;
  let fixture: ComponentFixture<ClientCourseDetailsWorkshopComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCourseDetailsWorkshopComponent]
    });
    fixture = TestBed.createComponent(ClientCourseDetailsWorkshopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
