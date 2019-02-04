package requests.spark;

import data.model.objects.Guest;
import data.model.objects.RSVP;
import data.model.objects.json.JSONContainer;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.annotations.RequestName;
import spark.Request;

@RequestName("rsvpSubmit")
public class RSVPAPI extends SparkRequest {
    private static Logger log = Logger.getLogger(RSVPAPI.class);

    public JSONContainer post(Request request) {
        RSVP rsvp = RSVP.create(RSVP.class);

        JSONContainer incomingRSVP = new JSONContainer().rawData(request.raw().getParameter("jsonRSVP"));

        JSONObject incomingRSVPObject = incomingRSVP.toJSONObject();
        rsvp.setGuestCount(Integer.parseInt(incomingRSVPObject.get("guestCount").toString()));

        JSONArray guests = incomingRSVPObject.getJSONArray("guests");

        for (int i = 0; i < guests.length(); i++) {
            JSONObject jsonGuest = guests.getJSONObject(i);
            Guest guest = Guest.create(Guest.class);

            if (jsonGuest.has("firstName")) {
                guest.setFirstName(jsonGuest.getString("firstName"));
            }
            if (jsonGuest.has("lastName")) {
                guest.setLastName(jsonGuest.getString("lastName"));
            }
            if (jsonGuest.has("email")) {
                guest.setEmail(jsonGuest.getString("email"));
            }
            if (jsonGuest.has("mehndiAccepted")) {
                guest.setMehndiAccepted(jsonGuest.getString("mehndiAccepted"));
            }
            if (jsonGuest.has("receptionAccepted")) {
                guest.setReceptionAccepted(jsonGuest.getString("receptionAccepted"));
            }
            if (jsonGuest.has("civilAccepted")) {
                guest.setCivilAccepted(jsonGuest.getString("civilAccepted"));
            }
            if (jsonGuest.has("rsvpType")) {
                guest.setRsvpType(jsonGuest.getString("rsvpType"));
            }

            guest.setParentRSVP(rsvp);
            guest.save();
        }

        rsvp.save();

        return new JSONContainer().OK();
    }
}
