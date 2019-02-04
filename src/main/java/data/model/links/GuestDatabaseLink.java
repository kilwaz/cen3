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
        link("rsvp_type", method("getRsvpType"), method("setRsvpType", String.class)); // 3
        link("first_name", method("getFirstName"), method("setFirstName", String.class)); // 4
        link("last_name", method("getLastName"), method("setLastName", String.class)); // 5
        link("email", method("getEmail"), method("setEmail", String.class)); // 6
        link("mehndiAccepted", method("getMehndiAccepted"), method("setMehndiAccepted", String.class)); // 7
        link("receptionAccepted", method("getReceptionAccepted"), method("setReceptionAccepted", String.class)); // 8
        link("civilAccepted", method("getCivilAccepted"), method("setCivilAccepted", String.class)); // 9
        link("whiteWine", method("getWhiteWine"), method("setWhiteWine", String.class)); // 10
        link("redWine", method("getRedWine"), method("setRedWine", String.class)); // 11
        link("roseWine", method("getRoseWine"), method("setRoseWine", String.class)); // 12
        link("beer", method("getBeer"), method("setBeer", String.class)); // 13
        link("vodka", method("getVodka"), method("setVodka", String.class)); // 14
        link("gin", method("getGin"), method("setGin", String.class)); // 15
        link("whiskey", method("getWhiskey"), method("setWhiskey", String.class)); // 16
        link("rum", method("getRum"), method("setRum", String.class)); // 17
        link("disaronno", method("getDisaronno"), method("setDisaronno", String.class)); // 18
        link("nonAlcoholicOption", method("getNonAlcoholicOption"), method("setNonAlcoholicOption", String.class)); // 19
        link("vegan", method("getVegan"), method("setVegan", String.class)); // 20
        link("jain", method("getJain"), method("setJain", String.class)); // 21
    }
}


