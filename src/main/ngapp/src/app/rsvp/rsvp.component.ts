import {Component, OnInit} from '@angular/core';
import {Rsvp} from "../rsvp";

@Component({
  selector: 'app-rsvp',
  templateUrl: './rsvp.component.html',
  styleUrls: ['./rsvp.component.css']
})
export class RsvpComponent implements OnInit {

  rsvp = new Rsvp();
  submitted = false;

  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true;
  }

  // TODO: Remove this when we're done
  get diagnostic() {
    return JSON.stringify(this.rsvp);
  }
}
