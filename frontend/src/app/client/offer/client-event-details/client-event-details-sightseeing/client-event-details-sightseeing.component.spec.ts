import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientEventDetailsSightseeingComponent} from './client-event-details-sightseeing.component';

describe('ClientEventDetailsSightseeingComponent', () => {
  let component: ClientEventDetailsSightseeingComponent;
  let fixture: ComponentFixture<ClientEventDetailsSightseeingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientEventDetailsSightseeingComponent]
    });
    fixture = TestBed.createComponent(ClientEventDetailsSightseeingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
