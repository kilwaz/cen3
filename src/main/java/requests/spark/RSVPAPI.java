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
            if (jsonGuest.has("whiteWine")) {
                guest.setWhiteWine(jsonGuest.getString("whiteWine"));
            }
            if (jsonGuest.has("redWine")) {
                guest.setRedWine(jsonGuest.getString("redWine"));
            }
            if (jsonGuest.has("roseWine")) {
                guest.setRoseWine(jsonGuest.getString("roseWine"));
            }
            if (jsonGuest.has("beer")) {
                guest.setBeer(jsonGuest.getString("beer"));
            }
            if (jsonGuest.has("vodka")) {
                guest.setVodka(jsonGuest.getString("vodka"));
            }
            if (jsonGuest.has("gin")) {
                guest.setGin(jsonGuest.getString("beer"));
            }
            if (jsonGuest.has("whiskey")) {
                guest.setWhiskey(jsonGuest.getString("whiskey"));
            }
            if (jsonGuest.has("rum")) {
                guest.setRum(jsonGuest.getString("rum"));
            }
            if (jsonGuest.has("disaronno")) {
                guest.setDisaronno(jsonGuest.getString("disaronno"));
            }
            if (jsonGuest.has("nonAlcoholicOption")) {
                guest.setNonAlcoholicOption(jsonGuest.getString("nonAlcoholicOption"));
            }
            if (jsonGuest.has("vegan")) {
                guest.setVegan(jsonGuest.getString("vegan"));
            }
            if (jsonGuest.has("jain")) {
                guest.setJain(jsonGuest.getString("jain"));
            }

            guest.setParentRSVP(rsvp);
            guest.save();
        }

        rsvp.save();

        return new JSONContainer().OK();
    }
}
