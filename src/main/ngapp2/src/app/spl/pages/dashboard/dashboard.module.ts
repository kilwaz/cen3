import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DashboardComponent} from './dashboard.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {InlineSVGModule} from 'ng-inline-svg-2';
import {StoreModule} from '@ngrx/store';
import {dashboardReducer} from './reducers/dashboard.reducers';
import {DashboardEffects} from './effects/dashboard.effects';
import {DashboardService} from './service/dashboard.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {EffectsModule} from '@ngrx/effects';

@NgModule({
  declarations: [DashboardComponent],
  providers: [
    DashboardService
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

    StoreModule.forFeature('dashboard', dashboardReducer),
    EffectsModule.forFeature([DashboardEffects]),

    RouterModule.forChild([
      {
        path: '',
        component: DashboardComponent,
      },
    ]),
  ],
})
export class DashboardModule {
}
