import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientEventDetailsBacheloretteComponent } from './client-event-details-bachelorette.component';

describe('ClientEventDetailsBacheloretteComponent', () => {
  let component: ClientEventDetailsBacheloretteComponent;
  let fixture: ComponentFixture<ClientEventDetailsBacheloretteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientEventDetailsBacheloretteComponent]
    });
    fixture = TestBed.createComponent(ClientEventDetailsBacheloretteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
