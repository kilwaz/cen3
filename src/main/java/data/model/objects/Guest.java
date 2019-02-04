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

    private String whiteWine = null;
    private String redWine = null;
    private String roseWine = null;

    private String beer = null;

    private String vodka = null;
    private String gin = null;
    private String whiskey = null;
    private String rum = null;
    private String disaronno = null;

    private String nonAlcoholicOption = null;

    private String vegan = null;
    private String jain = null;

    public Guest() {
        super();
    }

    public Guest(String firstName, String lastName, String email, RSVP parentRSVP, String mehndiAccepted, String receptionAccepted, String civilAccepted, String rsvpType, String whiteWine, String redWine, String roseWine, String beer, String vodka, String gin, String whiskey, String rum, String disaronno, String nonAlcoholicOption, String vegan, String jain) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.parentRSVP = parentRSVP;
        this.mehndiAccepted = mehndiAccepted;
        this.receptionAccepted = receptionAccepted;
        this.civilAccepted = civilAccepted;
        this.rsvpType = rsvpType;
        this.whiteWine = whiteWine;
        this.redWine = redWine;
        this.roseWine = roseWine;
        this.beer = beer;
        this.vodka = vodka;
        this.gin = gin;
        this.whiskey = whiskey;
        this.rum = rum;
        this.disaronno = disaronno;
        this.nonAlcoholicOption = nonAlcoholicOption;
        this.vegan = vegan;
        this.jain = jain;
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

    public String getWhiteWine() {
        return whiteWine;
    }

    public void setWhiteWine(String whiteWine) {
        this.whiteWine = whiteWine;
    }

    public String getRedWine() {
        return redWine;
    }

    public void setRedWine(String redWine) {
        this.redWine = redWine;
    }

    public String getRoseWine() {
        return roseWine;
    }

    public void setRoseWine(String roseWine) {
        this.roseWine = roseWine;
    }

    public String getBeer() {
        return beer;
    }

    public void setBeer(String beer) {
        this.beer = beer;
    }

    public String getVodka() {
        return vodka;
    }

    public void setVodka(String vodka) {
        this.vodka = vodka;
    }

    public String getGin() {
        return gin;
    }

    public void setGin(String gin) {
        this.gin = gin;
    }

    public String getWhiskey() {
        return whiskey;
    }

    public void setWhiskey(String whiskey) {
        this.whiskey = whiskey;
    }

    public String getRum() {
        return rum;
    }

    public void setRum(String rum) {
        this.rum = rum;
    }

    public String getDisaronno() {
        return disaronno;
    }

    public void setDisaronno(String disaronno) {
        this.disaronno = disaronno;
    }

    public String getNonAlcoholicOption() {
        return nonAlcoholicOption;
    }

    public void setNonAlcoholicOption(String nonAlcoholicOption) {
        this.nonAlcoholicOption = nonAlcoholicOption;
    }

    public String getVegan() {
        return vegan;
    }

    public void setVegan(String vegan) {
        this.vegan = vegan;
    }

    public String getJain() {
        return jain;
    }

    public void setJain(String jain) {
        this.jain = jain;
    }
}
