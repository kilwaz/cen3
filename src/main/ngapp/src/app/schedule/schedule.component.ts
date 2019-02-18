import {Component, OnInit} from '@angular/core';
import {ValidatedRSVP} from "../validatedRSVP";
import {SessionService} from "../session.service";

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

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
