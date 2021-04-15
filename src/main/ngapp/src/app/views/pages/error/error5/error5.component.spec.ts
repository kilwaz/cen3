import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { Error5Component } from './error5.component';

describe('Error5Component', () => {
  let component: Error5Component;
  let fixture: ComponentFixture<Error5Component>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ Error5Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Error5Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
