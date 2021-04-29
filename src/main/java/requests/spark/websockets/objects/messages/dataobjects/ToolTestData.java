package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class ToolTestData extends WebSocketData {
    @WSDataIncoming
    private Integer startYear = null;
    @WSDataIncoming
    private Integer startMonth = null;
    @WSDataIncoming
    private Integer startDay = null;
    @WSDataIncoming
    private Integer ageOnYear = null;
    @WSDataIncoming
    private Integer ageOnMonth = null;
    @WSDataIncoming
    private Integer ageOnDay = null;

    @WSDataOutgoing
    private Integer ageYears = null;
    @WSDataOutgoing
    private Integer ageMonths = null;
    @WSDataOutgoing
    private Integer ageDays = null;

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getAgeOnYear() {
        return ageOnYear;
    }

    public void setAgeOnYear(Integer ageOnYear) {
        this.ageOnYear = ageOnYear;
    }

    public Integer getAgeOnMonth() {
        return ageOnMonth;
    }

    public void setAgeOnMonth(Integer ageOnMonth) {
        this.ageOnMonth = ageOnMonth;
    }

    public Integer getAgeOnDay() {
        return ageOnDay;
    }

    public void setAgeOnDay(Integer ageOnDay) {
        this.ageOnDay = ageOnDay;
    }

    public Integer getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(Integer ageYears) {
        this.ageYears = ageYears;
    }

    public Integer getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(Integer ageMonths) {
        this.ageMonths = ageMonths;
    }

    public Integer getAgeDays() {
        return ageDays;
    }

    public void setAgeDays(Integer ageDays) {
        this.ageDays = ageDays;
    }
}
