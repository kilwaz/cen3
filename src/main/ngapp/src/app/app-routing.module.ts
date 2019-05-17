import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PlayerViewComponent} from "./player-view/player-view.component";
import {GameViewComponent} from "./game-view/game-view.component";
import {GameMasterComponent} from "./game-master/game-master.component";

const routes: Routes = [
  {path: '', redirectTo: '/player', pathMatch: 'full'},
  {path: 'game', component: GameViewComponent},
  {path: 'player', component: PlayerViewComponent},
  {path: 'gamemaster', component: GameMasterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
