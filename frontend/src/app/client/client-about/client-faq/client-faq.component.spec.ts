import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientFaqComponent} from './client-faq.component';

describe('ClientFaqComponent', () => {
  let component: ClientFaqComponent;
  let fixture: ComponentFixture<ClientFaqComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientFaqComponent]
    });
    fixture = TestBed.createComponent(ClientFaqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
