import {Component, ElementRef, Input, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebRecord} from "../../../../wsObjects/webRecord";
import {WebWorksheetConfig} from "../../../../wsObjects/webWorksheetConfig";


@Component({
  selector: '[worksheet-row]',
  templateUrl: './worksheet-row.component.html',
  styleUrls: ['./worksheet-row.component.scss'],
})
export class WorksheetRowComponent implements OnInit, OnDestroy {
  @Input('worksheetRowRecord') worksheetRowRecord: WebRecord;
  @Input('worksheetConfigs') worksheetConfigs: Array<WebWorksheetConfig>;

  // private fields
  private unsubscribe: Subscription[] = [];

  constructor(private renderer: Renderer2, private el: ElementRef) {
  }

  ngOnInit(): void {
    if (this.worksheetRowRecord.properties !== undefined) {
      this.renderer.addClass(this.el.nativeElement, 'iaColor');
    }
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
