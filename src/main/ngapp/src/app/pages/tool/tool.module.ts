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

@NgModule({
  declarations: [ToolComponent],
  providers: [
    ToolService
  ],
  imports: [
    CommonModule,
    FormsModule,
    GeneralModule,
    HighlightModule,
    NgbNavModule,
    NgbTooltipModule,
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
