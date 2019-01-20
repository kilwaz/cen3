package data.model.objects;

import data.model.DatabaseObject;
import data.model.objects.json.JSONMappable;
import org.apache.log4j.Logger;

import java.util.UUID;

public class RSVP extends DatabaseObject {
    private static Logger log = Logger.getLogger(Clip.class);

    private Integer guestCount = 0;

    public RSVP() {
        super();
    }

    public RSVP(UUID uuid, Integer guestCount) {
        super(uuid);
        this.guestCount = guestCount;
    }

    @JSONMappable("firstName")
    public Integer getGuestCount() {
        return this.guestCount;
    }

    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }
}
