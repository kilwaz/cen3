package data.model.objects.json;

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

public class JSONContainer<DatabaseObject> {
    private static Logger log = Logger.getLogger(JSONContainer.class);

    private List<DatabaseObject> data = new ArrayList<>();
    private String rawData = null;
    private String errorMessage = null;
    private List<String> filter = new ArrayList<>();
    private Integer status = null;

    public JSONContainer() {

    }

    public JSONContainer OK() {
        this.rawData = "{'response':'OK'}";
        return this;
    }

    public JSONContainer filter(String returnValuesStr) {
        if (returnValuesStr != null) {
            filter = Arrays.asList(returnValuesStr.split("\\s*,\\s*"));
        }
        return this;
    }

    public JSONContainer(String rawData) {
        this.rawData = rawData;
    }

    public JSONContainer(JSONObject jsonObject) {
        this.rawData = jsonObject.toString();
    }

    public JSONContainer dbDataList(List<DatabaseObject> dataList) {
        this.data = dataList;
        this.rawData = null;
        return this;
    }

    public JSONContainer status(Integer status) {
        this.status = status;
        return this;
    }

    public JSONContainer dbDataItem(DatabaseObject dataItem) {
        this.data = new ArrayList<>();
        this.data.add(dataItem);
        this.rawData = null;
        return this;
    }

    public JSONContainer error(String errorMessage) {
        this.data = new ArrayList<>();
        this.rawData = null;
        this.errorMessage = errorMessage;
        return this;
    }

    public JSONArray getData() {
        return JSONMapper.build().process(data, filter);
    }

    public JSONContainer rawData(String rawData) {
        this.rawData = rawData;
        this.data = null;
        return this;
    }

    public void writeToResponse(HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            if (rawData != null) {
                out.print(toJSONObject());
            } else {
                out.print(getData());
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
        } else {
            sb.append(getData());
        }

        return sb.toString();
    }

    public Boolean isError() {
        return errorMessage != null;
    }

    public void writeToResponseAsDataTable(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", getData());

        try {
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String writeToString() {
        return JSONMapper.build().process(data, filter).toString();
    }

    public JSONObject toJSONObject() {
        try {
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
