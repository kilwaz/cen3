import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {ManagementComponent} from './management.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {InlineSVGModule} from 'ng-inline-svg-2';
import {StoreModule} from '@ngrx/store';
import {managementReducer} from './reducers/management.reducers';
import {ManagementEffects} from './effects/management.effects';
import {ManagementService} from './service/management.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {EffectsModule} from '@ngrx/effects';

@NgModule({
  declarations: [ManagementComponent],
  providers: [
    ManagementService
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

    StoreModule.forFeature('management', managementReducer),
    EffectsModule.forFeature([ManagementEffects]),

    RouterModule.forChild([
      {
        path: '',
        component: ManagementComponent,
      },
    ]),
  ],
})
export class ManagementModule {
}
