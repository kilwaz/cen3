import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../services/websocket.service";
import {Echo} from "../wsActions/echo";
import {EchoPush} from "../wsActions/echoPush";
import {Message} from "../wsActions/message";

@Component({
  selector: 'app-echo',
  templateUrl: './echo.component.html',
  styleUrls: ['./echo.component.css']
})
export class EchoComponent implements OnInit {
  webSocketService: WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  ngOnInit() {
    EchoPush.registerListener("EchoPush", function (message: Message) {
      let echoPush: EchoPush = <EchoPush>message;

      console.log("Pushed message = " + echoPush.echoPushedMessage);

      // let question: Question = new Question().wsFill(nextQuestion);
      // _this.noQuestionsYet = false;
    });
  }

  echoPressed(button): void {
    let _this: EchoComponent = this;

    let echo: Echo = new Echo();
    this.webSocketService.sendCallback(echo, function (responseMessage) {
      let echoResponse: Echo = <Echo>responseMessage;
      console.log(echoResponse.echoResponse);
    });
  }
}
