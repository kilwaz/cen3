import {Component, OnInit} from '@angular/core';
import {TopLink} from "../topLink";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  links: TopLink[] = [
    new TopLink('Home', 'home', 'blue'),
    new TopLink('Schedule', 'schedule', 'light-blue'),
    new TopLink('Venue', 'venue', 'yellow'),
    new TopLink('Accommodation', 'accommodation', 'purple'),
    new TopLink('RSVP', 'rsvp', 'green'),
    new TopLink('FAQ', 'faq', 'red')
  ];

  constructor() {

  }

  ngOnInit() {
  }
}
