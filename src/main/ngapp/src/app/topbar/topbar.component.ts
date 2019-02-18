import {Component, OnInit} from '@angular/core';
import {TopLink} from "../topLink";
import {ValidatedRSVP} from "../validatedRSVP";
import {SessionService} from "../session.service";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  validatedRSVP: ValidatedRSVP = null;

  RSVP_TYPE_ALL: number = ValidatedRSVP.RSVP_TYPE_ALL;
  RSVP_TYPE_WEDDING_ONLY: number = ValidatedRSVP.RSVP_TYPE_WEDDING_ONLY;
  RSVP_TYPE_RECEPTION_ONLY: number = ValidatedRSVP.RSVP_TYPE_RECEPTION_ONLY;

  links: TopLink[] = null;

  constructor(sessionService: SessionService) {
    this.validatedRSVP = sessionService.validatedRSVP;

    this.links = [
      new TopLink('Home', 'home', 'blue'),
      new TopLink('Schedule', 'schedule', 'light-blue'),
      this.validatedRSVP.rsvpType == this.RSVP_TYPE_ALL ? new TopLink('Venues', 'venue', 'yellow') : new TopLink('Venue', 'venue', 'yellow'),
      new TopLink('Accommodation', 'accommodation', 'purple'),
      new TopLink('RSVP', 'rsvp', 'green'),
      new TopLink('FAQ', 'faq', 'red')
    ];
  }

  ngOnInit() {
  }
}
