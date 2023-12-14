import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Client404Component } from './client-404.component';

describe('Client404Component', () => {
  let component: Client404Component;
  let fixture: ComponentFixture<Client404Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Client404Component]
    });
    fixture = TestBed.createComponent(Client404Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
