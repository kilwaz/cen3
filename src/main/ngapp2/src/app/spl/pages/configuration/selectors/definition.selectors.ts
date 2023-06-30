import {definitionAdaptor} from "../reducers/configuration.reducers";

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = definitionAdaptor.getSelectors();
