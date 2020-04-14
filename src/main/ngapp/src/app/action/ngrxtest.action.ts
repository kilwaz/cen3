import {createAction, props} from '@ngrx/store';
import {RecordContainer} from "../containers/recordContainer";

export const NgrxtestAction = createAction('[Test Page] Testing', props<{ testString: string }>());
export const LoadRecordAction = createAction('[Test Page] Load Records');
export const LoadRecordSuccessAction = createAction('[WebSocket API] Records Loaded Success', props<{ payload: RecordContainer[] }>());
