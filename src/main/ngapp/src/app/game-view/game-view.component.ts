import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../websocket.service";
import {AdminGame} from "../wsObjects/adminGame";
import {NewPlayerJoined} from "../wsObjects/newPlayerJoined";
import {Message} from "../wsObjects/message";

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent implements OnInit {
  webSocketService: WebSocketService;
  webSocketServiceReference = WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit(): void {
    // let _this: GameViewComponent = this;
    this.webSocketService.sendCallback(new AdminGame(), function () {

    });

    NewPlayerJoined.registerListener(function (message: Message) {
      let newPlayerJoined: NewPlayerJoined = <NewPlayerJoined>message;

      console.log("The fuck this was a new function TYPE = " + newPlayerJoined.type + " and " + newPlayerJoined.newPlayerUUID);
    });
  }
}
