import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {EchoComponent} from "./echo/echo.component";
import {ClarityComponent} from "./clarity/clarity.component";

const routes: Routes = [
  // {path: '', redirectTo: '/', pathMatch: 'full'},
  {path: 'echo', component: EchoComponent},
  {path: 'clarity', component: ClarityComponent}
  // {path: 'gamemaster', component: GameMasterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
