import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientContactComponent } from './client-contact.component';

describe('ClientContactComponent', () => {
  let component: ClientContactComponent;
  let fixture: ComponentFixture<ClientContactComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientContactComponent]
    });
    fixture = TestBed.createComponent(ClientContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
