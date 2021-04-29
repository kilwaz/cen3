import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {GeneralModule} from '../../_metronic/partials/content/general/general.module';
import {ToolComponent} from './tool.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {HighlightModule} from 'ngx-highlightjs';
import {StoreModule} from '@ngrx/store';
import {toolReducer} from './reducers/tool.reducers';
import {ToolService} from './service/tool.service';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {MatFormFieldModule} from '@angular/material/form-field';

@NgModule({
  declarations: [ToolComponent],
  providers: [
    ToolService,
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
    {provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: {useUtc: true}},
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    GeneralModule,
    HighlightModule,
    NgbNavModule,
    NgbTooltipModule,
    MatInputModule,
    MatDatepickerModule,
    StoreModule.forFeature('tool', toolReducer),
    RouterModule.forChild([
      {
        path: '',
        component: ToolComponent,
      },
    ]),
  ],
})
export class ToolModule {
}
