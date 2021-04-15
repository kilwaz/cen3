import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ClarityComponent } from './clarity.component';

describe('ClarityComponent', () => {
  let component: ClarityComponent;
  let fixture: ComponentFixture<ClarityComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ClarityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClarityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
