import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './/app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {TopbarComponent} from './topbar/topbar.component';
import {FaqComponent} from './faq/faq.component';
import {HomeComponent} from './home/home.component';
import {RsvpComponent} from './rsvp/rsvp.component';
import {VenueComponent} from './venue/venue.component';
import {AuthComponent} from './auth/auth.component';
import {AccommodationComponent} from './accommodation/accommodation.component';
import {AgGridModule} from 'ag-grid-angular';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatInputModule, MatRadioModule, MatTreeModule} from '@angular/material';
import {ScheduleComponent} from './schedule/schedule.component';

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    FaqComponent,
    HomeComponent,
    RsvpComponent,
    VenueComponent,
    AuthComponent,
    AccommodationComponent,
    ScheduleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    AgGridModule.withComponents([]),
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatRadioModule,
    MatInputModule,
    MatTreeModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
