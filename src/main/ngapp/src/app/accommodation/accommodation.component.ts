import { Component, OnInit } from '@angular/core';
import {ValidatedRSVP} from "../validatedRSVP";
import {SessionService} from "../session.service";

@Component({
  selector: 'app-accommodation',
  templateUrl: './accommodation.component.html',
  styleUrls: ['./accommodation.component.css']
})
export class AccommodationComponent implements OnInit {

  validatedRSVP: ValidatedRSVP = null;
  RSVP_TYPE_ALL: number = ValidatedRSVP.RSVP_TYPE_ALL;
  RSVP_TYPE_WEDDING_ONLY: number = ValidatedRSVP.RSVP_TYPE_WEDDING_ONLY;
  RSVP_TYPE_RECEPTION_ONLY: number = ValidatedRSVP.RSVP_TYPE_RECEPTION_ONLY;

  constructor(sessionService: SessionService) {
    this.validatedRSVP = sessionService.validatedRSVP;
  }

  ngOnInit() {
  }

}
