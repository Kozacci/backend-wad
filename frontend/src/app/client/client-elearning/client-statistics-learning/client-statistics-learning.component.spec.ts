import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientStatisticsLearningComponent } from './client-statistics-learning.component';

describe('ClientStatisticsLearningComponent', () => {
  let component: ClientStatisticsLearningComponent;
  let fixture: ComponentFixture<ClientStatisticsLearningComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientStatisticsLearningComponent]
    });
    fixture = TestBed.createComponent(ClientStatisticsLearningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
