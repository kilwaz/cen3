import {Component, OnInit} from '@angular/core';
import {Source} from "../source";
import {SourceService} from "../source.service";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {
  sources: Source[];

  constructor(private sourceService: SourceService) {
  }

  getSources(): void {
    this.sourceService.getSources()
      .subscribe(sources => this.sources = sources);
  }

  ngOnInit() {
    this.getSources();
  }
}
