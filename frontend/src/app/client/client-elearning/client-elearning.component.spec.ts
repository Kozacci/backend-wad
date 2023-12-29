import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientElearningComponent} from './client-elearning.component';

describe('ClientElearningComponent', () => {
  let component: ClientElearningComponent;
  let fixture: ComponentFixture<ClientElearningComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientElearningComponent]
    });
    fixture = TestBed.createComponent(ClientElearningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
