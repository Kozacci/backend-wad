import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientEventDetailsScooterComponent} from './client-event-details-scooter.component';

describe('ClientEventDetailsScooterComponent', () => {
  let component: ClientEventDetailsScooterComponent;
  let fixture: ComponentFixture<ClientEventDetailsScooterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientEventDetailsScooterComponent]
    });
    fixture = TestBed.createComponent(ClientEventDetailsScooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
