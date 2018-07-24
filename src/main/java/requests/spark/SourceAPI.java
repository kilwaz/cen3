package requests.spark;

import data.model.dao.SourceDAO;
import data.model.objects.Source;
import data.model.objects.json.JSONContainer;
import requests.annotations.RequestName;
import spark.Request;

import java.util.List;
import java.util.UUID;

@RequestName("source")
public class SourceAPI extends SparkRequest {
    public JSONContainer get(Request request) {
        String uuid = request.queryParams("uuid");
        if (uuid != null) {
            if (!uuid.isEmpty()) {
                Source source = Source.load(UUID.fromString(uuid), Source.class);

                JSONContainer jsonContainer = new JSONContainer();
                jsonContainer.dbDataItem(source);
                return jsonContainer;
            }
        } else {
            SourceDAO sourceDAO = new SourceDAO();
            List<Source> sources = sourceDAO.getSources();

            JSONContainer jsonContainer = new JSONContainer();
            jsonContainer.dbDataList(sources);
            return jsonContainer;
        }

        return new JSONContainer();
    }
}
