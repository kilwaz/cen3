import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {HierarchyComponent} from './hierarchy.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {InlineSVGModule} from 'ng-inline-svg-2';
import {StoreModule} from '@ngrx/store';
import {hierarchyReducer} from './reducers/hierarchy.reducers';
import {HierarchyEffects} from './effects/hierarchy.effects';
import {HierarchyService} from './service/hierarchy.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {EffectsModule} from '@ngrx/effects';
import {ListItemComponent} from "./components/list-item/list-item.component";
import {ListItemContentComponent} from "./components/list-item-content/list-item-content.component";

@NgModule({
  declarations: [
    HierarchyComponent,
    ListItemComponent,
    ListItemContentComponent
  ],
  providers: [
    HierarchyService
  ],
  imports: [
    CommonModule,
    FormsModule,
    InlineSVGModule,
    NgbTooltipModule,

    MatButtonModule,
    MatFormFieldModule,
    NgbNavModule,
    MatInputModule,

    StoreModule.forFeature('hierarchy', hierarchyReducer),
    EffectsModule.forFeature([HierarchyEffects]),

    RouterModule.forChild([
      {
        path: '',
        component: HierarchyComponent,
      },
    ]),
  ],
})
export class HierarchyModule {
}
