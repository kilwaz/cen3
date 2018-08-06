package requests.spark;

import data.model.dao.SourceDAO;
import data.model.objects.Source;
import data.model.objects.json.JSONContainer;
import requests.annotations.RequestName;
import spark.Request;
import utils.managers.DatabaseObjectManager;

import java.util.List;
import java.util.UUID;

@RequestName("source")
public class SourceAPI extends SparkRequest {
    public JSONContainer get(Request request) {
        String uuidString = request.queryParams("uuid");

        if (uuidString != null) {
            UUID uuid;
            try {
                uuid = UUID.fromString(request.queryParams("uuid"));
            } catch (IllegalArgumentException ex) {
                return new JSONContainer().error("Invalid UUID");
            }

            if (DatabaseObjectManager.getInstance().objectExists(uuid)) { // Check if UUID is valid
                Source source = Source.load(uuid, Source.class);

                JSONContainer jsonContainer = new JSONContainer();
                jsonContainer.dbDataItem(source);
                return jsonContainer; // RETURN SINGLE SOURCE
            } else {
                return new JSONContainer().error("UUID Not found"); // RETURN NO RESULT
            }
        } else {
            SourceDAO sourceDAO = new SourceDAO();
            List<Source> sources = sourceDAO.getSources();

            JSONContainer jsonContainer = new JSONContainer();
            jsonContainer.dbDataList(sources);
            return jsonContainer; // RETURN ALL SOURCES
        }
    }
}