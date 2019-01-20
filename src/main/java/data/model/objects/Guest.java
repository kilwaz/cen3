package data.model.objects;

import data.model.DatabaseObject;
import data.model.objects.json.JSONMappable;
import org.apache.log4j.Logger;

import java.util.UUID;

public class Guest extends DatabaseObject {
    private static Logger log = Logger.getLogger(Clip.class);

    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private RSVP parentRSVP = null;

    private Integer mehndiAccepted = null;
    private Integer receptionAccepted = null;
    private Integer civilAccepted = null;

    public Guest() {
        super();
    }

    public Guest(UUID uuid, RSVP parentRSVP, String firstName, String lastName, String email, Integer mehndiAccepted, Integer receptionAccepted, Integer civilAccepted) {
        super(uuid);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mehndiAccepted = mehndiAccepted;
        this.receptionAccepted = receptionAccepted;
        this.civilAccepted = civilAccepted;
        this.parentRSVP = parentRSVP;
    }

    @JSONMappable("firstName")
    public String getFirstName() {
        return this.firstName;
    }

    @JSONMappable("lastName")
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RSVP getParentRSVP() {
        return parentRSVP;
    }

    public void setParentRSVP(RSVP parentRSVP) {
        this.parentRSVP = parentRSVP;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMehndiAccepted() {
        return mehndiAccepted;
    }

    public void setMehndiAccepted(Integer mehndiAccepted) {
        this.mehndiAccepted = mehndiAccepted;
    }

    public Integer getReceptionAccepted() {
        return receptionAccepted;
    }

    public void setReceptionAccepted(Integer receptionAccepted) {
        this.receptionAccepted = receptionAccepted;
    }

    public Integer getCivilAccepted() {
        return civilAccepted;
    }

    public void setCivilAccepted(Integer civilAccepted) {
        this.civilAccepted = civilAccepted;
    }
}
