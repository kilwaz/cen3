import {Component, OnInit, ViewChild} from '@angular/core';
import {ListComponent} from "../list/list.component";

@Component({
  selector: 'app-definition',
  templateUrl: './definition.component.html',
  styleUrls: ['./definition.component.css']
})
export class DefinitionComponent implements OnInit {

  @ViewChild(ListComponent) listComponent: ListComponent;

  constructor() {
  }

  ngOnInit(): void {
  }

}
