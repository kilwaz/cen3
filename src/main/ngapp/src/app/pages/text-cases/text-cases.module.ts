import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {GeneralModule} from '../../_metronic/partials/content/general/general.module';
import {TextCasesComponent} from './text-cases.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {HighlightModule} from 'ngx-highlightjs';
import {StoreModule} from '@ngrx/store';
import {textCasesReducer} from './reducers/text-cases.reducers';
import {TextCasesEffects} from './effects/text-cases.effects';
import {TextCasesService} from './service/text-cases.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {EffectsModule} from '@ngrx/effects';

@NgModule({
  declarations: [TextCasesComponent],
  providers: [
    TextCasesService
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    GeneralModule,
    HighlightModule,
    NgbNavModule,
    NgbTooltipModule,
    MatInputModule,

    StoreModule.forFeature('textCases', textCasesReducer),
    EffectsModule.forFeature([TextCasesEffects]),
    RouterModule.forChild([
      {
        path: '',
        component: TextCasesComponent,
      },
    ]),
  ],
})
export class TextCasesModule {
}
