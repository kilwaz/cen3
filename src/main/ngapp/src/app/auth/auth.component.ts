import {Component, OnInit} from '@angular/core';
import {ValidatedRSVP} from "../validatedRSVP";
import {SessionService} from "../session.service";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  validatedRSVP: ValidatedRSVP;
  sessionService: SessionService;

  password: string = "";

  constructor(sessionService: SessionService) {
    this.sessionService = sessionService;
    this.validatedRSVP = this.sessionService.validatedRSVP;
  }

  checkPassword() {
    if (this.password == "Aqua") {
      this.validatedRSVP.validated = true;
      this.validatedRSVP.rsvpType = ValidatedRSVP.RSVP_TYPE_ALL;
    } else if (this.password == "Beach") {
      this.validatedRSVP.validated = true;
      this.validatedRSVP.rsvpType = ValidatedRSVP.RSVP_TYPE_WEDDING_ONLY;
    } else {
      this.validatedRSVP.validated = false;
    }

    this.sessionService.validatedRSVP = this.validatedRSVP;
  }

  ngOnInit(): void {
  }
}
