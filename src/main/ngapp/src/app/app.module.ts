import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './/app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {OverlayContainer} from '@angular/cdk/overlay';

import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatRadioModule} from '@angular/material/radio';
import {MatTreeModule} from '@angular/material/tree';

import {EchoComponent} from './echo/echo.component';
import {ClarityComponent} from './clarity/clarity.component';
import {DndDirective} from './dnd.directive';
import {MatIconModule} from "@angular/material/icon";
import {RecordComponent} from './record/record.component';
import {MatTableModule} from "@angular/material/table";
import {DefinitionComponent} from './components/definition/definition.component';
import {PropertiesComponent} from './components/properties/properties.component';
import {PropertyComponent} from './components/property/property.component';
import {MatExpansionModule} from "@angular/material/expansion";
import { ListComponent } from './components/list/list.component';
import {MatListModule} from "@angular/material/list";

@NgModule({
  declarations: [
    AppComponent,
    EchoComponent,
    ClarityComponent,
    DndDirective,
    RecordComponent,
    DefinitionComponent,
    PropertiesComponent,
    PropertyComponent,
    ListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatRadioModule,
    MatInputModule,
    MatTreeModule,
    MatButtonToggleModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    MatIconModule,
    MatTableModule,
    MatExpansionModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  //TODO: Not convinced this does shit..
  constructor(overlayContainer: OverlayContainer) {
    overlayContainer.getContainerElement().classList.add('indigo-pink');
  }
}
