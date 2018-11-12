package data.model.objects;

import data.model.DatabaseObject;
import data.model.objects.json.JSONMappable;
import org.apache.log4j.Logger;

import java.util.UUID;

public class QRCode extends DatabaseObject {
    private static Logger log = Logger.getLogger(Clip.class);

    private String url = "";

    public QRCode() {
        super();
    }

    public QRCode(UUID uuid, String url) {
        super(uuid);
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JSONMappable("url")
    public String url() {
        return url;
    }
}
