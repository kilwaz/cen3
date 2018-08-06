import {Component, OnInit} from '@angular/core';
import {PrivateService, Source} from "..";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css'],
  providers: [PrivateService]
})

export class EditorComponent implements OnInit {
  sources: Source[];
  selectedSource: Source;

  constructor(private privateService: PrivateService) {
  }

  getSources(): void {
    this.privateService.getSource(undefined, "uuid,name,url").subscribe(sources => this.sources = sources);
  }

  ngOnInit() {
    this.getSources();
  }

  public static toTimeString(time): string {
    let timeHour = Math.floor(time / 3600);
    let timeHourTens = Math.floor(timeHour / 10 % 10);
    let timeHourOnes = Math.floor(timeHour % 10);
    let timeMin = Math.floor((time / 60) % 60);
    let timeMinTens = Math.floor(timeMin / 10 % 10);
    let timeMinOnes = Math.floor(timeMin % 10);
    let timeSec = Math.floor(time % 60);
    let timeSecTens = Math.floor(timeSec / 10 % 10);
    let timeSecOnes = Math.floor(timeSec % 10);
    let timeFrame = Math.floor((time % 1) * 23.98); // FRAMES!!!!
    let timeFrameTens = Math.floor(timeFrame / 10 % 10);
    let timeFrameOnes = Math.floor(timeFrame % 10);

    return timeHourTens + timeHourOnes + "h" + timeMinTens + timeMinOnes + "m" + timeSecTens + timeSecOnes + "s" + timeFrameTens + timeFrameOnes + "f";
  };
}
