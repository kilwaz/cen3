import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'clarity-property',
  templateUrl: './property.component.html',
  styleUrls: ['./property.component.css']
})
export class PropertyComponent implements OnInit {

  panelOpenState: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
