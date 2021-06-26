// NGRX
// import { routerReducer } from '@ngrx/router-store';
// import { ActionReducerMap, MetaReducer } from '@ngrx/store';
// import { storeFreeze } from 'ngrx-store-freeze';
//
// import { environment } from '../../environments/environment';
import {ToolState} from './tool/reducers/tool.reducers';
import {TextCasesState} from './text-cases/reducers/text-cases.reducers';

// tslint:disable-next-line:no-empty-interface
export interface AppState {
  tool: ToolState;
  textCases: TextCasesState;
}

// export const reducers: ActionReducerMap<AppState> = { router: routerReducer };
//
// export const metaReducers: Array<MetaReducer<AppState>> = !environment.production ? [storeFreeze] : [];
