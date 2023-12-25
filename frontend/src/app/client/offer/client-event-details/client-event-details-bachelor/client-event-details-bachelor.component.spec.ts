import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientEventDetailsBachelorComponent} from './client-event-details-bachelor.component';

describe('ClientEventDetailsBachelorComponent', () => {
  let component: ClientEventDetailsBachelorComponent;
  let fixture: ComponentFixture<ClientEventDetailsBachelorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientEventDetailsBachelorComponent]
    });
    fixture = TestBed.createComponent(ClientEventDetailsBachelorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
