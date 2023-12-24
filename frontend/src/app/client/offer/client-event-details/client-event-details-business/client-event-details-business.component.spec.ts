import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientEventDetailsBusinessComponent } from './client-event-details-business.component';

describe('ClientEventDetailsBusinessComponent', () => {
  let component: ClientEventDetailsBusinessComponent;
  let fixture: ComponentFixture<ClientEventDetailsBusinessComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientEventDetailsBusinessComponent]
    });
    fixture = TestBed.createComponent(ClientEventDetailsBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
