package data.model.objects;

import data.model.DatabaseObject;
import org.apache.log4j.Logger;

public class Guest extends DatabaseObject {
    private static Logger log = Logger.getLogger(Clip.class);

    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private RSVP parentRSVP = null;

    private String mehndiAccepted = null;
    private String receptionAccepted = null;
    private String civilAccepted = null;

    private String rsvpType = null;

    public Guest() {
        super();
    }

    public Guest(String firstName, String lastName, String email, RSVP parentRSVP, String mehndiAccepted, String receptionAccepted, String civilAccepted, String rsvpType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.parentRSVP = parentRSVP;
        this.mehndiAccepted = mehndiAccepted;
        this.receptionAccepted = receptionAccepted;
        this.civilAccepted = civilAccepted;
        this.rsvpType = rsvpType;
    }

    public String getRSVPUUID() {
        if (parentRSVP != null) {
            return parentRSVP.getUuidString();
        }

        return null;
    }

    public void setRSVPUUID(RSVP parentRSVP) {
        this.parentRSVP = parentRSVP;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RSVP getParentRSVP() {
        return parentRSVP;
    }

    public void setParentRSVP(RSVP parentRSVP) {
        this.parentRSVP = parentRSVP;
    }

    public String getMehndiAccepted() {
        return mehndiAccepted;
    }

    public void setMehndiAccepted(String mehndiAccepted) {
        this.mehndiAccepted = mehndiAccepted;
    }

    public String getReceptionAccepted() {
        return receptionAccepted;
    }

    public void setReceptionAccepted(String receptionAccepted) {
        this.receptionAccepted = receptionAccepted;
    }

    public String getCivilAccepted() {
        return civilAccepted;
    }

    public void setCivilAccepted(String civilAccepted) {
        this.civilAccepted = civilAccepted;
    }

    public String getRsvpType() {
        return rsvpType;
    }

    public void setRsvpType(String rsvpType) {
        this.rsvpType = rsvpType;
    }
}
