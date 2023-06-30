// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  ConfigurationActionTypes,
  LoadDefinitions,
  LoadRecordDefinitions,
  RequestDefinitions,
  RequestRecordDefinitions,
  SaveDefinition
} from '../actions/configuration.actions';
import {ConfigurationService} from '../service/configuration.service';
import {ConfigurationState} from "../reducers/configuration.reducers";
import {selectConfiguration} from "../selectors/configuration.selectors";

@Injectable()
export class ConfigurationEffects {
  constructor(private actions$: Actions,
              private configurationService: ConfigurationService, private store: Store<ConfigurationState>) {
  }

  requestRecordDefinitions$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestRecordDefinitions>(ConfigurationActionTypes.RequestRecordDefinitions),
      concatLatestFrom(() =>
        this.store.pipe(select(selectConfiguration))
      ),
      map(() => {
        this.configurationService.requestRecordDefinitions()
          .pipe(
            tap(result => {
              this.store.dispatch(new LoadRecordDefinitions({
                recordDefinitions: result.recordDefinitions
              }));
            }),
          ).subscribe();
      }));
  }, {dispatch: false});

  requestDefinitions$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestDefinitions>(ConfigurationActionTypes.RequestDefinitions),
      concatLatestFrom(() =>
        this.store.pipe(select(selectConfiguration))
      ),
      map(([requestDefinitions]) => {
        this.configurationService.requestDefinitions(requestDefinitions.payload.recordDefinition.name)
          .pipe(
            tap(result => {
              this.store.dispatch(new LoadDefinitions({
                definitions: result.definitions
              }));
            }),
          ).subscribe();
      }));
  }, {dispatch: false});

  saveDefinition$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<SaveDefinition>(ConfigurationActionTypes.SaveDefinition),
      concatLatestFrom(() =>
        this.store.pipe(select(selectConfiguration))
      ),
      map(([saveDefinition, state]) => {
        this.configurationService.saveDefinition(state.definitions.entities[state.selectedDefinition.uuid]);
      }));
  }, {dispatch: false});
}
