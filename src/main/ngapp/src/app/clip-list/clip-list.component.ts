import {Component, Input, OnInit} from '@angular/core';
import {Mark, PrivateService, Source} from "../index";
import {EditorComponent} from "../editor/editor.component";

@Component({
  selector: 'clip-list',
  templateUrl: './clip-list.component.html',
  styleUrls: ['./clip-list.component.css'],
  providers: [PrivateService]
})
export class ClipListComponent implements OnInit {
  @Input() selectedSource: Source;

  player: HTMLVideoElement;

  constructor(private privateService: PrivateService) {
    console.log("constructor " + this.selectedSource);
  }

  ngOnInit() {
    this.player = document.getElementsByTagName("video")[0];
    console.log("ngoninit " + this.selectedSource.clips);
    this.privateService.getClip(this.selectedSource.uuid).subscribe(clipList => {
      this.selectedSource.clips = clipList;
      console.log("After return " + this.selectedSource.clips);
      debugger;
    });
  }

  formatTime(time) {
    return EditorComponent.toTimeString(time);
  }

  createClip(): void {
    if (this.selectedSource.clips === undefined) {
      this.selectedSource.clips = Array<Mark>();
    }
    this.privateService.createClip(this.selectedSource.uuid).subscribe(createdClip => {
      this.selectedSource.clips = this.selectedSource.clips.concat(createdClip);
    });
  }

  setEndMark(clip): void {
    this.privateService.updateClip(clip.uuid, null, clip.endMark.uuid).subscribe(response => {
    });
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
    this.privateService.deleteClip(clip.uuid).subscribe(response => {
      this.selectedSource.clips = this.selectedSource.clips.filter(item => item !== clip);
    });
  }

  setStartMark(clip): void {
    this.privateService.updateClip(clip.uuid, clip.startMark.uuid).subscribe(response => {
    });
  }
}
