import {worksheetConfigAdaptor} from "../reducers/configuration.reducers";

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = worksheetConfigAdaptor.getSelectors();
