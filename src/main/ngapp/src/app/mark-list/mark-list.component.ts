import {Component, Input, OnInit} from '@angular/core';
import {Mark, PrivateService, Source} from "..";
import {EditorComponent} from "../editor/editor.component";

@Component({
  selector: 'mark-list',
  templateUrl: './mark-list.component.html',
  styleUrls: ['./mark-list.component.css'],
  providers: [PrivateService]
})
export class MarkListComponent implements OnInit {
  @Input() selectedSource: Source;

  player: HTMLVideoElement;

  constructor(private privateService: PrivateService) {
  }

  ngOnInit() {
    this.player = document.getElementsByTagName("video")[0];
    this.privateService.getMark(this.selectedSource.uuid).subscribe(markList => {
      this.selectedSource.marks = markList;
    });
  }

  createMark(): void {
    if (this.selectedSource.marks === undefined) {
      this.selectedSource.marks = Array<Mark>();
    }
    this.privateService.createMark(this.selectedSource.uuid, this.player.currentTime).subscribe(createdMark => {
      this.selectedSource.marks = this.selectedSource.marks.concat(createdMark);
    });
  }

  jumpToMark(mark): void {
    this.player.currentTime = mark.time;
  }

  deleteMark(mark): void {
    this.privateService.deleteMark(mark.uuid).subscribe(response => {
      this.selectedSource.marks = this.selectedSource.marks.filter(item => item !== mark);
    });
  }

  formatTime(time) {
    return EditorComponent.toTimeString(time);
  }
}
