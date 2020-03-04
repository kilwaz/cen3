import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ClarityComponent} from "./clarity/clarity.component";
import {RecordComponent} from "./record/record.component";
import {DefinitionComponent} from "./components/definition/definition.component";

const routes: Routes = [
  // {path: '', redirectTo: '/', pathMatch: 'full'},
  // {path: 'echo', component: EchoComponent},
  {path: 'clarity', component: ClarityComponent},
  {path: 'definition', component: DefinitionComponent},
  {path: 'record', component: RecordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
