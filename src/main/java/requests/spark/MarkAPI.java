package requests.spark;

import data.model.dao.MarkDAO;
import data.model.objects.Mark;
import data.model.objects.Source;
import data.model.objects.json.JSONContainer;
import org.apache.log4j.Logger;
import requests.annotations.RequestName;
import spark.Request;
import utils.managers.DatabaseObjectManager;

import java.util.List;
import java.util.UUID;

@RequestName("mark")
public class MarkAPI extends SparkRequest {
    private static Logger log = Logger.getLogger(MarkAPI.class);

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

                List<Mark> markList = new MarkDAO().getMarksFromSource(source);

                JSONContainer jsonContainer = new JSONContainer();
                jsonContainer.dbDataList(markList);
                return jsonContainer; // RETURN LIST OF MARKS
            } else {
                return new JSONContainer().error("UUID Not found"); // RETURN NO RESULT
            }
        }

        return new JSONContainer().error("Invalid UUID");
    }

    public JSONContainer put(Request request) {
        if (request.queryParams("time") == null) {
            return new JSONContainer().error("Missing time parameter");
        } else if (request.queryParams("uuid") == null) {
            return new JSONContainer().error("Missing UUID parameter");
        }

        Double time = Double.parseDouble(request.queryParams("time"));
        UUID uuid;
        try {
            uuid = UUID.fromString(request.queryParams("uuid"));
        } catch (IllegalArgumentException ex) {
            return new JSONContainer().error("Invalid UUID");
        }

        if (DatabaseObjectManager.getInstance().objectExists(uuid)) { // Check if UUID is valid
            Source source = Source.load(uuid, Source.class);

            Mark newMark = Mark.create(Mark.class);
            newMark.setSource(source);
            newMark.setTime(time);
            newMark.save();

            JSONContainer jsonContainer = new JSONContainer();
            jsonContainer.dbDataItem(newMark);
            return jsonContainer; // RETURN NEWLY CREATED SINGLE MARK
        } else {
            return new JSONContainer().error("UUID Not found"); // RETURN NO RESULT
        }
    }

    public JSONContainer delete(Request request) {
        if (request.queryParams("uuid") == null) {
            return new JSONContainer().error("Missing UUID parameter");
        }

        UUID uuid;
        try {
            uuid = UUID.fromString(request.queryParams("uuid"));
        } catch (IllegalArgumentException ex) {
            return new JSONContainer().error("Invalid UUID");
        }

        Mark mark = Mark.load(uuid, Mark.class);
        log.info("Deleting mark " + mark.getUuidString());
        mark.delete();

        return new JSONContainer().OK();
    }
}