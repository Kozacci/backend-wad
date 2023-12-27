import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientMyEventsComponent } from './client-my-events.component';

describe('ClientMyEventsComponent', () => {
  let component: ClientMyEventsComponent;
  let fixture: ComponentFixture<ClientMyEventsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientMyEventsComponent]
    });
    fixture = TestBed.createComponent(ClientMyEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
