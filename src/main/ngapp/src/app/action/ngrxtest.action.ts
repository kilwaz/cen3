import {createAction, props} from '@ngrx/store';

export const ngrxtestAction = createAction('[Test Page] Testing', props<{ testString: string }>());
export const loadRecordAction = createAction('[Test Page] Load Records');
