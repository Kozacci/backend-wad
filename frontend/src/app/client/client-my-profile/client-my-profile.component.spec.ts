import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientMyProfileComponent} from './client-my-profile.component';

describe('ClientMyProfileComponent', () => {
  let component: ClientMyProfileComponent;
  let fixture: ComponentFixture<ClientMyProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientMyProfileComponent]
    });
    fixture = TestBed.createComponent(ClientMyProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
