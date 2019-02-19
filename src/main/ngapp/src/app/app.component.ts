import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: []
})
export class AppComponent {
  constructor() {
    let webSocket = new WebSocket("ws://localhost:4568/echo");
    webSocket.onopen = function () {
      webSocket.send("Hello we are connected");
    };
  }
}
