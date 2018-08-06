import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorPlayerComponent } from './editor-player.component';

describe('EditorPlayerComponent', () => {
  let component: EditorPlayerComponent;
  let fixture: ComponentFixture<EditorPlayerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditorPlayerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorPlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
