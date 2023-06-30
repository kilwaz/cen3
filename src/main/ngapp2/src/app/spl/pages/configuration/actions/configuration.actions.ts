import {Action} from '@ngrx/store';
import {RecordDefinitionDataItem} from "../../../wsObjects/recordDefinitionDataItem";
import {DefinitionDataItem} from "../../../wsObjects/definitionDataItem";
import {HierarchyListItem} from "../../../wsObjects/hierarchyListItem";

export enum ConfigurationActionTypes {
  RequestRecordDefinitions = '[Configuration-RequestRecordDefinitions] Action',
  LoadRecordDefinitions = '[Configuration-LoadRecordDefinitions] Action',

  RequestDefinitions = '[Configuration-RequestDefinitions] Action',
  LoadDefinitions = '[Configuration-LoadDefinitions] Action',

  SelectDefinition = '[Configuration-SelectDefinition] Action',
  UpdateDefinition= '[Configuration-UpdateDefinition] Action',
  SaveDefinition = '[Configuration-SaveDefinition] Action'
}

export class RequestRecordDefinitions implements Action {
  readonly type = ConfigurationActionTypes.RequestRecordDefinitions;

  constructor(public payload: {}) {
  }
}

export class LoadRecordDefinitions implements Action {
  readonly type = ConfigurationActionTypes.LoadRecordDefinitions;

  constructor(public payload: {
    recordDefinitions: Array<RecordDefinitionDataItem>
  }) {
  }
}

export class RequestDefinitions implements Action {
  readonly type = ConfigurationActionTypes.RequestDefinitions;

  constructor(public payload: {
    recordDefinition: RecordDefinitionDataItem
  }) {
  }
}

export class LoadDefinitions implements Action {
  readonly type = ConfigurationActionTypes.LoadDefinitions;

  constructor(public payload: {
    definitions: Array<DefinitionDataItem>
  }) {
  }
}

export class SelectDefinition implements Action {
  readonly type = ConfigurationActionTypes.SelectDefinition;

  constructor(public payload: {
    definition: DefinitionDataItem
  }) {
  }
}

export class SaveDefinition implements Action {
  readonly type = ConfigurationActionTypes.SaveDefinition;

  constructor(public payload: {
    definition: DefinitionDataItem
  }) {
  }
}

export class UpdateDefinition implements Action {
  readonly type = ConfigurationActionTypes.UpdateDefinition;

  constructor(public payload: {
    update: { id: string, changes: Partial<DefinitionDataItem> }
  }) {
  }
}

export type ConfigurationActions =
  RequestRecordDefinitions |
  LoadRecordDefinitions |

  RequestDefinitions |
  LoadDefinitions |

  SelectDefinition |
  SaveDefinition |
  UpdateDefinition;
