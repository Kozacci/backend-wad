import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientEventDetailsSunsetComponent } from './client-event-details-sunset.component';

describe('ClientEventDetailsSunsetComponent', () => {
  let component: ClientEventDetailsSunsetComponent;
  let fixture: ComponentFixture<ClientEventDetailsSunsetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientEventDetailsSunsetComponent]
    });
    fixture = TestBed.createComponent(ClientEventDetailsSunsetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
