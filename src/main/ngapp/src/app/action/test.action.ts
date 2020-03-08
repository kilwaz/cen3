import {createAction, props} from '@ngrx/store';

export const testAction = createAction('[Test Page] Testing', props<{ testString: string }>());
