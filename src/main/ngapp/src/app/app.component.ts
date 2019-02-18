import {Component} from '@angular/core';
import {ValidatedRSVP} from "./validatedRSVP";
import {SessionService} from "./session.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [SessionService]
})
export class AppComponent {
  validatedRSVP: ValidatedRSVP;

  constructor(sessionService: SessionService) {
    this.validatedRSVP = new ValidatedRSVP();
    sessionService.validatedRSVP = this.validatedRSVP;
  }
}
