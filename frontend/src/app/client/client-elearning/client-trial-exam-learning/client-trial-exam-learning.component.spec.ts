import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientTrialExamLearningComponent } from './client-trial-exam-learning.component';

describe('ClientTrialExamLearningComponent', () => {
  let component: ClientTrialExamLearningComponent;
  let fixture: ComponentFixture<ClientTrialExamLearningComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientTrialExamLearningComponent]
    });
    fixture = TestBed.createComponent(ClientTrialExamLearningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
