import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './/app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCheckboxModule,
  MatInputModule,
  MatRadioModule,
  MatTreeModule
} from '@angular/material';
import {GameComponent} from './game/game.component';
import {PlayerComponent} from './player/player.component';
import {PlayerViewComponent} from './player-view/player-view.component';
import {GameViewComponent} from './game-view/game-view.component';
import {ScoreboardComponent} from './scoreboard/scoreboard.component';
import { GameMasterComponent } from './game-master/game-master.component';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    PlayerComponent,
    PlayerViewComponent,
    GameViewComponent,
    ScoreboardComponent,
    GameMasterComponent
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
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
