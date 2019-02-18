package data.model.links;


import data.model.DatabaseLink;
import data.model.objects.RSVP;

import java.util.UUID;

public class RSVPDatabaseLink extends DatabaseLink {
    public RSVPDatabaseLink() {
        super("rsvp", RSVP.class);

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("guest_count", method("getGuestCount"), method("setGuestCount", Integer.class)); // 2
    }
}
