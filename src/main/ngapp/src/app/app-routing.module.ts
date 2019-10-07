import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {EchoComponent} from "./echo/echo.component";

const routes: Routes = [
  // {path: '', redirectTo: '/', pathMatch: 'full'},
  {path: 'echo', component: EchoComponent}
  // {path: 'player', component: PlayerViewComponent},
  // {path: 'gamemaster', component: GameMasterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
