import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './/app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {OverlayContainer} from '@angular/cdk/overlay';
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCheckboxModule,
  MatInputModule,
  MatProgressBarModule,
  MatRadioModule,
  MatTreeModule
} from '@angular/material';
import {EchoComponent} from './echo/echo.component';
import {ClarityComponent} from './clarity/clarity.component';
import {DndDirective} from './dnd.directive';
import {ParseTreeNodeComponent} from './parse-tree-node/parse-tree-node.component';
import {MatIconModule} from "@angular/material/icon";

@NgModule({
  declarations: [
    AppComponent,
    EchoComponent,
    ClarityComponent,
    DndDirective,
    ParseTreeNodeComponent
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
    MatIconModule
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
