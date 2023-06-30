import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {ConfigurationComponent} from './configuration.component';
import {FormsModule} from '@angular/forms';
import {NgbNavModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {InlineSVGModule} from 'ng-inline-svg-2';
import {StoreModule} from '@ngrx/store';
import {ConfigurationEffects} from './effects/configuration.effects';
import {ConfigurationService} from './service/configuration.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {EffectsModule} from '@ngrx/effects';
import {configurationReducer} from "./reducers/configuration.reducers";

@NgModule({
  declarations: [ConfigurationComponent],
  providers: [
    ConfigurationService
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

    StoreModule.forFeature('configuration', configurationReducer),
    EffectsModule.forFeature([ConfigurationEffects]),

    RouterModule.forChild([
      {
        path: '',
        component: ConfigurationComponent,
      },
    ]),
  ],
})
export class ConfigurationModule {
}
