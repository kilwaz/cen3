package requests.spark;

import data.model.dao.ClipDAO;
import data.model.objects.Clip;
import data.model.objects.Mark;
import data.model.objects.Source;
import data.model.objects.json.JSONContainer;
import org.apache.log4j.Logger;
import requests.annotations.RequestName;
import spark.Request;
import utils.managers.DatabaseObjectManager;

import java.util.List;
import java.util.UUID;

@RequestName("clip")
public class ClipAPI extends SparkRequest {
    private static Logger log = Logger.getLogger(ClipAPI.class);

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

                List<Clip> clipList = new ClipDAO().getClipsFromSource(source);

                JSONContainer jsonContainer = new JSONContainer();
                jsonContainer.dbDataList(clipList);
                return jsonContainer; // RETURN SINGLE SOURCE
            } else {
                return new JSONContainer().error("UUID Not found"); // RETURN NO RESULT
            }
        }

        return new JSONContainer().error("Invalid UUID");
    }

    public JSONContainer put(Request request) {
        if (request.queryParams("uuid") == null) {
            return new JSONContainer().error("Missing UUID parameter");
        }

        UUID uuid;
        try {
            uuid = UUID.fromString(request.queryParams("uuid"));
        } catch (IllegalArgumentException ex) {
            return new JSONContainer().error("Invalid UUID");
        }

        if (DatabaseObjectManager.getInstance().objectExists(uuid)) { // Check if UUID is valid
            Source source = Source.load(uuid, Source.class);

            Clip newClip = Clip.create(Clip.class);
            newClip.setSource(source);
            newClip.save();

            JSONContainer jsonContainer = new JSONContainer();
            jsonContainer.dbDataItem(newClip);
            return jsonContainer; // RETURN NEWLY CREATED SINGLE CLIP
        } else {
            return new JSONContainer().error("UUID Not found"); // RETURN NO RESULT
        }
    }

    public JSONContainer post(Request request) {
        if (request.queryParams("uuid") == null) {
            return new JSONContainer().error("Missing UUID parameter");
        }

        UUID uuid;
        try {
            uuid = UUID.fromString(request.queryParams("uuid"));
        } catch (IllegalArgumentException ex) {
            return new JSONContainer().error("Invalid UUID");
        }

        Clip clip = Clip.load(uuid, Clip.class);

        if (request.queryParams("startMark") != null && !request.queryParams("startMark").equals("null")) {
            UUID startMarkUUID = UUID.fromString(request.queryParams("startMark"));
            Mark startMark = Mark.load(startMarkUUID, Mark.class);

            if (startMark != null) {
                clip.setStartMark(startMark);
            }
        }
        if (request.queryParams("endMark") != null && !request.queryParams("endMark").equals("null")) {
            UUID endMarkUUID = UUID.fromString(request.queryParams("endMark"));
            Mark endMark = Mark.load(endMarkUUID, Mark.class);

            if (endMark != null) {
                clip.setEndMark(endMark);
            }
        }
        clip.save();

        return new JSONContainer().OK();
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

        Clip clip = Clip.load(uuid, Clip.class);
        log.info("Deleting clip " + clip.getUuidString());
        clip.delete();

        return new JSONContainer().OK();
    }
}
