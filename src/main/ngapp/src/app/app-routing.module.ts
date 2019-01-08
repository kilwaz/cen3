import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {FaqComponent} from "./faq/faq.component";
import {HomeComponent} from "./home/home.component";
import {RsvpComponent} from "./rsvp/rsvp.component";
import {AuthComponent} from "./auth/auth.component";
import {VenueComponent} from "./venue/venue.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'faq', component: FaqComponent},
  {path: 'rsvp', component: RsvpComponent},
  {path: 'auth', component: AuthComponent},
  {path: 'venue', component: VenueComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
