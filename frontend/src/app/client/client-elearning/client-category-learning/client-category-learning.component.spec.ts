import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientCategoryLearningComponent } from './client-category-learning.component';

describe('ClientCategoryLearningComponent', () => {
  let component: ClientCategoryLearningComponent;
  let fixture: ComponentFixture<ClientCategoryLearningComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientCategoryLearningComponent]
    });
    fixture = TestBed.createComponent(ClientCategoryLearningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
