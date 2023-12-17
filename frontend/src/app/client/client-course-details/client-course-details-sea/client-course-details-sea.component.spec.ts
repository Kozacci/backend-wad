import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientCourseDetailsSeaComponent } from './client-course-details-sea.component';

describe('ClientCourseDetailsSeaComponent', () => {
  let component: ClientCourseDetailsSeaComponent;
  let fixture: ComponentFixture<ClientCourseDetailsSeaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCourseDetailsSeaComponent]
    });
    fixture = TestBed.createComponent(ClientCourseDetailsSeaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
