import { Component, OnInit } from '@angular/core';
import {ValidatedRSVP} from "../validatedRSVP";
import {SessionService} from "../session.service";

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  validatedRSVP: ValidatedRSVP = new ValidatedRSVP();
  RSVP_TYPE_ALL: number = ValidatedRSVP.RSVP_TYPE_ALL;

  constructor(sessionService: SessionService) {
    this.validatedRSVP = sessionService.validatedRSVP;
  }

  ngOnInit() {
  }

}
