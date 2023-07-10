import {Action} from '@ngrx/store';
import {RecordDefinitionDataItem} from "../../../wsObjects/recordDefinitionDataItem";
import {DefinitionDataItem} from "../../../wsObjects/definitionDataItem";
import {FormulaContextDataItem} from "../../../wsObjects/formulaContextDataItem";
import {WorksheetConfigDataItem} from "../../../wsObjects/worksheetConfigDataItem";
import {WebWorksheetConfigDataItem} from "../../../wsObjects/webWorksheetConfigDataItem";


export enum ConfigurationActionTypes {
  RequestRecordDefinitions = '[Configuration-RequestRecordDefinitions] Action',
  LoadRecordDefinitions = '[Configuration-LoadRecordDefinitions] Action',

  RequestFormulaContexts = '[Configuration-RequestFormulaContexts] Action',
  LoadFormulaContexts = '[Configuration-LoadFormulaContexts] Action',

  RequestDefinitions = '[Configuration-RequestDefinitions] Action',
  LoadDefinitions = '[Configuration-LoadDefinitions] Action',

  RequestWorksheetConfigs = '[Configuration-RequestWorksheetConfigs] Action',
  LoadWorksheetConfigs = '[Configuration-LoadWorksheetConfigs] Action',

  SelectType = '[Configuration-SelectType] Action',
  SelectDefinition = '[Configuration-SelectDefinition] Action',
  SelectWorksheetConfig = '[Configuration-SelectWorksheetConfig] Action',

  UpdateWorksheetConfig = '[Configuration-UpdateWorksheetConfig] Action',

  UpdateDefinition = '[Configuration-UpdateDefinition] Action',
  SaveDefinition = '[Configuration-SaveDefinition] Action',

  AddNewWorksheetConfig = '[Configuration-AddNewWorksheetConfig] Action'
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

export class RequestFormulaContexts implements Action {
  readonly type = ConfigurationActionTypes.RequestFormulaContexts;

  constructor(public payload: {}) {
  }
}

export class LoadFormulaContexts implements Action {
  readonly type = ConfigurationActionTypes.LoadFormulaContexts;

  constructor(public payload: {
    formulaContexts: Array<FormulaContextDataItem>
  }) {
  }
}

export class RequestWorksheetConfigs implements Action {
  readonly type = ConfigurationActionTypes.RequestWorksheetConfigs;

  constructor(public payload: {}) {
  }
}

export class LoadWorksheetConfigs implements Action {
  readonly type = ConfigurationActionTypes.LoadWorksheetConfigs;

  constructor(public payload: {
    worksheetConfigs: Array<WorksheetConfigDataItem>
  }) {
  }
}

export class RequestDefinitions implements Action {
  readonly type = ConfigurationActionTypes.RequestDefinitions;

  constructor(public payload: {
    type: string,
    recordDefinition: RecordDefinitionDataItem,
    formulaContext: FormulaContextDataItem
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

export class SelectType implements Action {
  readonly type = ConfigurationActionTypes.SelectType;

  constructor(public payload: {
    selectedType: string
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

export class SelectWorksheetConfig implements Action {
  readonly type = ConfigurationActionTypes.SelectWorksheetConfig;

  constructor(public payload: {
    worksheetConfig: WorksheetConfigDataItem
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

export class UpdateWorksheetConfig implements Action {
  readonly type = ConfigurationActionTypes.UpdateWorksheetConfig;

  constructor(public payload: {
    update: { id: string, changes: Partial<DefinitionDataItem> }
  }) {
  }
}

export class AddNewWorksheetConfig implements Action {
  readonly type = ConfigurationActionTypes.AddNewWorksheetConfig;

  constructor(public payload: {
    worksheetConfigName: string,
    definitionId: string,
  }) {
  }
}

export type ConfigurationActions =
  RequestRecordDefinitions |
  LoadRecordDefinitions |

  RequestFormulaContexts |
  LoadFormulaContexts |

  RequestDefinitions |
  LoadDefinitions |

  RequestWorksheetConfigs |
  LoadWorksheetConfigs |

  SelectType |
  SelectDefinition |
  SelectWorksheetConfig |

  UpdateWorksheetConfig |

  SaveDefinition |
  UpdateDefinition |

  AddNewWorksheetConfig;
