import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientEventDetailsComponent } from './client-event-details.component';

describe('ClientEventDetailsComponent', () => {
  let component: ClientEventDetailsComponent;
  let fixture: ComponentFixture<ClientEventDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientEventDetailsComponent]
    });
    fixture = TestBed.createComponent(ClientEventDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
