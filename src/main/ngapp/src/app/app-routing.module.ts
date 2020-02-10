import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {EchoComponent} from "./echo/echo.component";
import {ClarityComponent} from "./clarity/clarity.component";
import {RecordComponent} from "./record/record.component";

const routes: Routes = [
  // {path: '', redirectTo: '/', pathMatch: 'full'},
  {path: 'echo', component: EchoComponent},
  {path: 'clarity', component: ClarityComponent},
  {path: 'record', component: RecordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
