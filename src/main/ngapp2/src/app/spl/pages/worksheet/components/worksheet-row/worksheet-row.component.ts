import {Component, ElementRef, Input, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {Subscription} from 'rxjs';
import {WebRecordDataItem} from "../../../../wsObjects/webRecordDataItem";
import {WebWorksheetConfigDataItem} from "../../../../wsObjects/webWorksheetConfigDataItem";


@Component({
  selector: '[worksheet-row]',
  templateUrl: './worksheet-row.component.html',
  styleUrls: ['./worksheet-row.component.scss'],
})
export class WorksheetRowComponent implements OnInit, OnDestroy {
  @Input('worksheetRowRecord') worksheetRowRecord: WebRecordDataItem;
  @Input('worksheetConfigs') worksheetConfigs: Array<WebWorksheetConfigDataItem>;

  // private fields
  private unsubscribe: Subscription[] = [];

  constructor(private renderer: Renderer2, private el: ElementRef) {
  }

  ngOnInit(): void {
    document.documentElement.style.setProperty('--main-color', '#a088a8');

    if (this.worksheetRowRecord.properties !== undefined) {
      this.renderer.addClass(this.el.nativeElement, 'iaColor');
    }
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
