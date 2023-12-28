import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientMyCoursesComponent } from './client-my-courses.component';

describe('ClientMyCoursesComponent', () => {
  let component: ClientMyCoursesComponent;
  let fixture: ComponentFixture<ClientMyCoursesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientMyCoursesComponent]
    });
    fixture = TestBed.createComponent(ClientMyCoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
