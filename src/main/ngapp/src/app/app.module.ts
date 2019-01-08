import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './/app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {TopbarComponent} from './topbar/topbar.component';
import {FooterComponent} from './footer/footer.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {FaqComponent} from './faq/faq.component';
import { HomeComponent } from './home/home.component';
import { RsvpComponent } from './rsvp/rsvp.component';
import { VenueComponent } from './venue/venue.component';
import { AuthComponent } from './auth/auth.component';
import {Globals} from "./globals";

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    FooterComponent,
    SidebarComponent,
    FaqComponent,
    HomeComponent,
    RsvpComponent,
    VenueComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule {
}
