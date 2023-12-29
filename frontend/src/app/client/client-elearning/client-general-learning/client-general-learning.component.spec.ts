import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientGeneralLearningComponent } from './client-general-learning.component';

describe('ClientGeneralLearningComponent', () => {
  let component: ClientGeneralLearningComponent;
  let fixture: ComponentFixture<ClientGeneralLearningComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientGeneralLearningComponent]
    });
    fixture = TestBed.createComponent(ClientGeneralLearningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
