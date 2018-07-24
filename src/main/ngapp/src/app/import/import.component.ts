import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.css']
})
export class ImportComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

  sendImportFile(): void {
    alert("clicked the button to reset the database");
  }
}
