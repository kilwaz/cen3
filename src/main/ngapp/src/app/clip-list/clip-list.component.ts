import {Component, Input, OnInit} from '@angular/core';
import {Source} from "../index";
import {EditorComponent} from "../editor/editor.component";

@Component({
  selector: 'clip-list',
  templateUrl: './clip-list.component.html',
  styleUrls: ['./clip-list.component.css']
})
export class ClipListComponent implements OnInit {
  @Input() selectedSource: Source;

  constructor() {
  }

  ngOnInit() {
  }

  formatTime(time) {
    return EditorComponent.toTimeString(time);
  }

  createClip(): void {

  }

  setClipEnd(clip): void {

  }

  lockInClip(clip): void {

  }

  unlockClip(clip): void {

  }

  splitClip(clip): void {

  }

  finaliseClip(clip): void {

  }

  deleteClip(clip): void {

  }

  setClipStart(clip): void {

  }
}
