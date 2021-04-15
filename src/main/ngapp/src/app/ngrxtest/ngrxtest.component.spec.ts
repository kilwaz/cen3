import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { NgrxtestComponent } from './ngrxtest.component';

describe('NgrxtestComponent', () => {
  let component: NgrxtestComponent;
  let fixture: ComponentFixture<NgrxtestComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ NgrxtestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgrxtestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
