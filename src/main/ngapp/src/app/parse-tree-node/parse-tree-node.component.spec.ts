import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParseTreeNodeComponent } from './parse-tree-node.component';

describe('ParseTreeNodeComponent', () => {
  let component: ParseTreeNodeComponent;
  let fixture: ComponentFixture<ParseTreeNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParseTreeNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParseTreeNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
