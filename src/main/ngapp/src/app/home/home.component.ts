import {Component, OnInit} from '@angular/core';
import {timer} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  timeRemaining: number = 0;

  weddingDate: Date;
  currentDate: Date;

  remainingSeconds: number;
  remainingMinutes: number;
  remainingHours: number;
  remainingDays: number;

  constructor() {

  }

  ngOnInit() {
    this.currentDate = new Date();
    this.weddingDate = new Date("2019-05-26T13:00:00");

    this.setupTimer();
    this.calcTimeNumbers();
  }

  setupTimer() {
    const source = timer(1000, 1000);
    const abc = source.subscribe(val => {
      this.currentDate = new Date();
      this.calcTimeNumbers();
    });
  }

  calcTimeNumbers() {
    this.timeRemaining = Math.round((this.weddingDate.getTime() - this.currentDate.getTime()) / 1000);
    let dividedTime = this.timeRemaining;

    this.remainingDays = Math.floor(dividedTime / (60 * 60 * 24));
    dividedTime = dividedTime - this.remainingDays * (60 * 60 * 24);
    this.remainingHours = Math.floor(dividedTime / (60 * 60));
    dividedTime = dividedTime - this.remainingHours * (60 * 60);
    this.remainingMinutes = Math.floor(dividedTime / 60);
    dividedTime = dividedTime - this.remainingMinutes * 60;
    this.remainingSeconds = dividedTime;

  }
}
