package data.model.links;


import data.model.DatabaseLink;
import data.model.objects.Guest;
import data.model.objects.RSVP;

import java.util.UUID;

public class GuestDatabaseLink extends DatabaseLink {
    public GuestDatabaseLink() {
        super("guest", Guest.class);

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("rsvp_id", method("getRSVPUUID"), method("setRSVPUUID", RSVP.class)); // 2
        link("first_name", method("getFirstName"), method("setFirstName", String.class)); // 3
        link("last_name", method("getLastName"), method("setLastName", String.class)); // 4
        link("email", method("getEmail"), method("setEmail", String.class)); // 5
        link("mehndiAccepted", method("getMehndiAccepted"), method("setMehndiAccepted", Integer.class)); // 6
        link("receptionAccepted", method("getReceptionAccepted"), method("setReceptionAccepted", Integer.class)); // 7
        link("civilAccepted", method("getCivilAccepted"), method("setCivilAccepted", Integer.class)); // 8
    }
}


