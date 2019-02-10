import {Component, OnInit} from '@angular/core';
import {ValidatedRSVP} from "../validatedRSVP";
import {SessionService} from "../session.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  validatedRSVP: ValidatedRSVP;
  sessionService: SessionService;

  passwordValidator = new FormControl('', []);

  password: string = "";

  constructor(sessionService: SessionService) {
    this.sessionService = sessionService;
    this.validatedRSVP = this.sessionService.validatedRSVP;

    this.passwordValidator.valueChanges.subscribe(form => {
      this.password = this.passwordValidator.value;
    });
  }

  checkPassword() {
    if (this.password == "Aqua2019") {
      this.validatedRSVP.validated = true;
      this.validatedRSVP.rsvpType = ValidatedRSVP.RSVP_TYPE_ALL;
    } else if (this.password == "Beach2019") {
      this.validatedRSVP.validated = true;
      this.validatedRSVP.rsvpType = ValidatedRSVP.RSVP_TYPE_WEDDING_ONLY;
    } else if (this.password == "Sand2019") {
      this.validatedRSVP.validated = true;
      this.validatedRSVP.rsvpType = ValidatedRSVP.RSVP_TYPE_RECEPTION_ONLY;
    } else {
      this.validatedRSVP.validated = false;
      this.passwordValidator.setValue("");
    }

    this.sessionService.validatedRSVP = this.validatedRSVP;
  }

  enterPressedCheck(event) {
    if (event.keyCode == 13) {
      this.checkPassword();
    }
  }

  ngOnInit(): void {
  }
}
