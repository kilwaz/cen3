import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ImportComponent} from "./import/import.component";
import {EditorComponent} from "./editor/editor.component";
import {PeopleComponent} from "./people/people.component";

const routes: Routes = [
  {path: '', redirectTo: '/editor', pathMatch: 'full'},
  {path: 'import', component: ImportComponent},
  {path: 'people', component: PeopleComponent},
  {path: 'editor', component: EditorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
