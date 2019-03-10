import {Component} from '@angular/core';
import {WebSocketService} from "./websocket.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: []
})
export class AppComponent {


  constructor(private webSocketService: WebSocketService) {

    webSocketService.getHello();
  }
}
