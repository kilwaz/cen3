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

    this.links = [];
    this.links.push(new TopLink('Home', 'home', 'blue'));
    this.links.push(new TopLink('Schedule', 'schedule', 'light-blue'));
    if (this.validatedRSVP.rsvpType == this.RSVP_TYPE_ALL) {
      this.links.push(new TopLink('Venues', 'venue', 'yellow'));
    } else {
      this.links.push(new TopLink('Venue', 'venue', 'yellow'));
    }
    this.links.push(new TopLink('Accommodation', 'accommodation', 'purple'));
    if (this.validatedRSVP.rsvpType == this.RSVP_TYPE_ALL || this.validatedRSVP.rsvpType == this.RSVP_TYPE_WEDDING_ONLY) {
      this.links.push(new TopLink('Photo list', 'photolist', 'green'));
    }
    this.links.push(new TopLink('FAQ', 'faq', 'red'));
    this.links.push()
  }

  ngOnInit() {
  }
}
