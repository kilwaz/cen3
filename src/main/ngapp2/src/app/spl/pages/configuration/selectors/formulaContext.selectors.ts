import {formulaContextAdaptor} from "../reducers/configuration.reducers";

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = formulaContextAdaptor.getSelectors();
