import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './/app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {TopbarComponent} from './topbar/topbar.component';
import {FooterComponent} from './footer/footer.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {ImportComponent} from './import/import.component';
import {ProcessesComponent} from './processes/processes.component';
import {EditorComponent} from './editor/editor.component';
import {PeopleComponent} from './people/people.component';


@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    FooterComponent,
    SidebarComponent,
    ImportComponent,
    ProcessesComponent,
    EditorComponent,
    PeopleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
