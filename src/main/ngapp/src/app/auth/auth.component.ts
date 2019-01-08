import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  @Input() validated: boolean;
  @Output() validatedOutput = new EventEmitter<boolean>();

  password: string = "";

  constructor() {
  }

  checkPassword() {
    if (this.password == "Aqua" || this.password == "Beach") {
      this.validated = true;
      this.validatedOutput.emit(this.validated);
    }
  }

  ngOnInit(): void {
  }
}
