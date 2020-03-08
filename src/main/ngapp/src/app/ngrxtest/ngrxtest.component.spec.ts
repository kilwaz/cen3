import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgrxtestComponent } from './ngrxtest.component';

describe('NgrxtestComponent', () => {
  let component: NgrxtestComponent;
  let fixture: ComponentFixture<NgrxtestComponent>;

  beforeEach(async(() => {
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
