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
  player: HTMLVideoElement;
  duration: number;

  constructor(private privateService: PrivateService) {
  }

  getSources(): void {
    this.privateService.getSource(undefined, "uuid,name,url").subscribe(sources => this.sources = sources);
  }

  timeUpdate(): void {
    if (this.player === undefined) {
      this.player = document.getElementById("playerElement") as HTMLVideoElement;
    }
    this.duration = this.player.currentTime;
  }

  ngOnInit() {
    this.getSources();
  }
}
