import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {SummaryComponent} from './summary.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {InlineSVGModule} from 'ng-inline-svg-2';
import {StoreModule} from '@ngrx/store';
import {summaryReducer} from './reducers/summary.reducers';
import {SummaryEffects} from './effects/summary.effects';
import {SummaryService} from './service/summary.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {EffectsModule} from '@ngrx/effects';

@NgModule({
  declarations: [SummaryComponent],
  providers: [
    SummaryService
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

    StoreModule.forFeature('summaryReducer', summaryReducer),
    EffectsModule.forFeature([SummaryEffects]),

    RouterModule.forChild([
      {
        path: '',
        component: SummaryComponent,
      },
    ]),
  ],
})
export class SummaryModule {
}
