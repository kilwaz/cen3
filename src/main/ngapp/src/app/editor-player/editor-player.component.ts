import {Component, Input, OnInit} from '@angular/core';
import {Source} from "..";

@Component({
  selector: 'editor-player',
  templateUrl: './editor-player.component.html',
  styleUrls: ['./editor-player.component.css']
})
export class EditorPlayerComponent implements OnInit {
  @Input() selectedSource: Source;

  player: HTMLVideoElement;

  frameRate = 23.98;

  timeFrameOnes: number = 0;
  timeFrameTens: number = 0;
  timeSecOnes: number = 0;
  timeSecTens: number = 0;
  timeMinOnes: number = 0;
  timeMinTens: number = 0;
  timeHourOnes: number = 0;
  timeHourTens: number = 0;

  duration: number = 0;

  constructor() {
  }

  ngOnInit() {
  }

  setSourceTime(): void {
    this.player.currentTime = this.convertToSeconds();
  }

  convertToSeconds(): number {
    return this.timeSecOnes
      + (this.timeSecTens * 10)
      + (this.timeMinOnes * 60)
      + (this.timeMinTens * 600)
      + (this.timeHourOnes * 3600)
      + (this.timeHourTens * 36000)
      + (((this.timeFrameTens * 10) + this.timeFrameOnes) / this.frameRate);
  };

  timeUpdate(): void {
    if (this.player === undefined) {
      this.player = document.getElementById("playerElement") as HTMLVideoElement;
    }
    this.duration = this.player.currentTime;

    let time = this.player.currentTime;

    let timeHour = Math.floor(time / 3600);
    this.timeHourTens = Math.floor(timeHour / 10 % 10);
    this.timeHourOnes = Math.floor(timeHour % 10);
    let timeMin = Math.floor((time / 60) % 60);
    this.timeMinTens = Math.floor(timeMin / 10 % 10);
    this.timeMinOnes = Math.floor(timeMin % 10);
    let timeSec = Math.floor(time % 60);
    this.timeSecTens = Math.floor(timeSec / 10 % 10);
    this.timeSecOnes = Math.floor(timeSec % 10);
    let timeFrame = Math.floor((time % 1) * this.frameRate);
    this.timeFrameTens = Math.floor(timeFrame / 10 % 10);
    this.timeFrameOnes = Math.floor(timeFrame % 10);
  }
}
