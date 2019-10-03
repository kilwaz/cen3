import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: '', redirectTo: '/', pathMatch: 'full'}
  // {path: 'game', component: GameViewComponent},
  // {path: 'player', component: PlayerViewComponent},
  // {path: 'gamemaster', component: GameMasterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
