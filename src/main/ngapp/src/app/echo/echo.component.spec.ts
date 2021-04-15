import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { EchoComponent } from './echo.component';

describe('EchoComponent', () => {
  let component: EchoComponent;
  let fixture: ComponentFixture<EchoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EchoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EchoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
