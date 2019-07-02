package data.model.objects.json;

import data.model.DatabaseObject;
import error.Error;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JSONContainer<DBO extends DatabaseObject> {
    private static Logger log = Logger.getLogger(JSONContainer.class);

    private List<DBO> dataObjectList = new ArrayList<>();
    private DBO dataObject = null;
    private String rawData = null;
    private String errorMessage = null;
    private List<String> filter = new ArrayList<>();
    private Integer status = null;

    public JSONContainer() {

    }

    public JSONContainer(String rawData) {
        this.rawData = rawData;
    }

    public JSONContainer(JSONObject jsonObject) {
        this.rawData = jsonObject.toString();
    }

    public JSONContainer OK() {
        log.info("Doing an OK response");
        this.rawData = "{'response':'OK'}";
        return this;
    }

    public JSONContainer filter(String returnValuesStr) {
        if (returnValuesStr != null) {
            filter = Arrays.asList(returnValuesStr.split("\\s*,\\s*"));
        }
        return this;
    }

    public JSONContainer dbDataList(List<DBO> dataList) {
        this.dataObjectList = dataList;
        this.rawData = null;
        return this;
    }

    public JSONContainer status(Integer status) {
        this.status = status;
        return this;
    }

    public JSONContainer dbDataItem(DBO dataItem) {
        this.dataObject = dataItem;
        this.rawData = null;
        return this;
    }

    public JSONContainer error(String errorMessage) {
        this.dataObjectList = new ArrayList<>();
        this.rawData = null;
        this.errorMessage = errorMessage;
        return this;
    }

    private JSONArray getDataObjectList() {
        return JSONMapper.build().process(dataObjectList, filter);
    }

    private JSONObject getDataObject() {
        return JSONMapper.build().process(dataObject, filter);
    }

    public JSONContainer rawData(String rawData) {
        this.rawData = rawData;
        this.dataObjectList = null;
        return this;
    }

    public void writeToResponse(HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            if (rawData != null) {
                out.print(toJSONObject());
            } else {
                out.print(getDataObjectList());
            }

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String writeResponse() {
        StringBuilder sb = new StringBuilder();
        if (errorMessage != null) {
            sb.append(new JSONObject().append("error", errorMessage));
        } else if (rawData != null) {
            sb.append(toJSONObject());
        } else if (dataObject != null) {
            sb.append(getDataObject());
        } else {
            sb.append(getDataObjectList());
        }

        return sb.toString();
    }

    public Boolean isError() {
        return errorMessage != null;
    }

    public void writeToResponseAsDataTable(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", getDataObjectList());

        try {
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String writeToString() {
        return JSONMapper.build().process(dataObjectList, filter).toString();
    }

    public JSONObject toJSONObject() {
        try {
            rawData = rawData.replace("\\\"", "\""); // Remove any escaping, better way to do this?
            if (rawData.startsWith("\"")) { // For some reason the message starts with a "
                rawData = rawData.substring(1);
            }
            if (rawData.endsWith("\"")) { // For some reason the message ends with a "
                rawData = rawData.substring(0, rawData.length() - 1);
            }

            return new JSONObject(Objects.requireNonNullElse(rawData, ""));
        } catch (JSONException ex) {
            try {
                if (rawData != null && !rawData.isEmpty()) {
                    new JSONObject(rawData);
                } else {
                    new JSONObject();
                }
            } catch (JSONException ex1) {
                Error.NOT_VALID_JSON.record().additionalInformation("JSON - " + rawData).create(ex1);
            }
        }
        return new JSONObject();
    }

    public Integer getStatus() {
        return status;
    }
}
