import {worksheetConfigItemsAdaptor} from "../reducers/configuration.reducers";

export const {
  selectAll,
  selectEntities,
  selectIds,
  selectTotal
} = worksheetConfigItemsAdaptor.getSelectors();
