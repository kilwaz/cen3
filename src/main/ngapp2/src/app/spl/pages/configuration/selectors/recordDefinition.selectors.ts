import {recordDefinitionAdaptor} from "../reducers/configuration.reducers";

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = recordDefinitionAdaptor.getSelectors();
