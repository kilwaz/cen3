// Angular
import {Injectable} from '@angular/core';
// RxJS
import {map, tap} from 'rxjs/operators';
// NGRX
import {Actions, concatLatestFrom, createEffect, ofType} from '@ngrx/effects';
import {select, Store} from '@ngrx/store';
// Actions
import {
  AddNewWorksheetConfig,
  ConfigurationActionTypes,
  LoadDefinitions,
  LoadFormulaContexts,
  LoadRecordDefinitions,
  LoadWorksheetConfigs,
  RequestDefinitions,
  RequestFormulaContexts,
  RequestRecordDefinitions,
  RequestWorksheetConfigs,
  SaveDefinition,
  SelectType
} from '../actions/configuration.actions';
import {ConfigurationService} from '../service/configuration.service';
import {ConfigurationState} from "../reducers/configuration.reducers";
import {selectConfiguration} from "../selectors/configuration.selectors";
import {Observable} from "rxjs";
import {RequestDefinitions as RequestDefinitionsWs} from "../../../wsActions/requestDefinitions";

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

  selectType$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<SelectType>(ConfigurationActionTypes.SelectType),
      concatLatestFrom(() =>
        this.store.pipe(select(selectConfiguration))
      ),
      tap(([, configurationState]) => {
        if (configurationState.selectedType == 'Record') {
          this.store.dispatch(new RequestRecordDefinitions({}));
        } else if (configurationState.selectedType == 'Formula Context') {
          this.store.dispatch(new RequestFormulaContexts({}));
        }
      }));
  }, {dispatch: false});

  requestFormulaContexts$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestFormulaContexts>(ConfigurationActionTypes.RequestFormulaContexts),
      concatLatestFrom(() =>
        this.store.pipe(select(selectConfiguration))
      ),
      map(() => {
        this.configurationService.requestFormulaContexts()
          .pipe(
            tap(result => {
              this.store.dispatch(new LoadFormulaContexts({
                formulaContexts: result.formulaContexts
              }));
            }),
          ).subscribe();
      }));
  }, {dispatch: false});

  requestWorksheetConfigs$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<RequestWorksheetConfigs>(ConfigurationActionTypes.RequestWorksheetConfigs),
      concatLatestFrom(() =>
        this.store.pipe(select(selectConfiguration))
      ),
      map(() => {
        this.configurationService.requestWorksheetConfigs()
          .pipe(
            tap(result => {
              this.store.dispatch(new LoadWorksheetConfigs({
                worksheetConfigs: result.worksheetConfigs
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
        let requestDefinitions$: Observable<RequestDefinitionsWs> = null;
        if (requestDefinitions.payload.type == 'Record') {
          requestDefinitions$ = this.configurationService.requestDefinitions(requestDefinitions.payload.recordDefinition.name, 'Record');
        } else if (requestDefinitions.payload.type == 'FormulaContext') {
          requestDefinitions$ = this.configurationService.requestDefinitions(requestDefinitions.payload.formulaContext.name, 'FormulaContext');
        } else if (requestDefinitions.payload.type == 'All') {
          requestDefinitions$ = this.configurationService.requestAllDefinitions();
        }

        requestDefinitions$.pipe(
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

  addNewWorksheetConfig$ = createEffect(() => {
    return this.actions$.pipe(
      ofType<AddNewWorksheetConfig>(ConfigurationActionTypes.AddNewWorksheetConfig),
      concatLatestFrom(() =>
        this.store.pipe(select(selectConfiguration))
      ),
      map(([addNewWorksheetConfig, state]) => {
        this.configurationService.updateWorksheetConfig(addNewWorksheetConfig.payload.worksheetConfigName, addNewWorksheetConfig.payload.definitionId).pipe(
          tap(() => {
            this.store.dispatch(new RequestWorksheetConfigs({}));
          }),
        ).subscribe();
      }));
  }, {dispatch: false});
}
