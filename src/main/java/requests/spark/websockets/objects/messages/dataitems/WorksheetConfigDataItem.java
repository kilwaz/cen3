package requests.spark.websockets.objects.messages.dataitems;

import clarity.definition.WorksheetConfig;
import clarity.definition.WorksheetConfigDetails;
import data.model.dao.WorksheetConfigDetailsDAO;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.ArrayList;
import java.util.List;

public class WorksheetConfigDataItem extends JSONWeb {
    @WSDataReference()
    private String name = null;

    @WSDataReference()
    @WSDataJSONArrayClass(WebWorksheetConfigDataItem.class)
    @WSDataTypeScriptClass(WebWorksheetConfigDataItem.class)
    private List<WebWorksheetConfigDataItem> worksheetConfigDetails = null;

    public WorksheetConfigDataItem(WorksheetConfig worksheetConfig) {
        this.name = worksheetConfig.getName();
        List<WorksheetConfigDetails> worksheetConfigDetails = new WorksheetConfigDetailsDAO().getWorksheetConfigDetails(worksheetConfig);

        List<WebWorksheetConfigDataItem> worksheetConfigDataItems = new ArrayList<>();
        for (WorksheetConfigDetails worksheetConfigDetail : worksheetConfigDetails) {

            worksheetConfigDataItems.add(new WebWorksheetConfigDataItem(worksheetConfigDetail));
        }

        this.worksheetConfigDetails = worksheetConfigDataItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WebWorksheetConfigDataItem> getWorksheetConfigDetails() {
        return worksheetConfigDetails;
    }

    public void setWorksheetConfigDetails(List<WebWorksheetConfigDataItem> worksheetConfigDetails) {
        this.worksheetConfigDetails = worksheetConfigDetails;
    }
}
