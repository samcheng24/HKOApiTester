package com.sam.models;

import java.util.List;

/**
 * Model class to define structure of the API Response
 */
public class WeatherResponse {
    // default values of enroller request
    private String lightning;
    private Object rainfall; // TODO: convert object to sub-model classes
    private List<Integer> icon;
    private String iconUpdateTime;
    private String uvindex;
    private String updateTime;
    private String warningMessage;
    private String rainstormReminder;
    private String specialWxTips;
    private String txmessage;
    private String mintempFrom00To09;
    private String rainfallFrom00To12;
    private String rainfallLastMonth;
    private String rainfallJanuaryToLastMonth;
    private Object temperature; // TODO: convert object to sub-model classes
    private Object humidity; // TODO: convert object to sub-model classes

    // Getters
    public String getLightning() {
        return lightning;
    }

    public Object getRainfall() {
        return rainfall;
    }

    public List<Integer> getIcon() {
        return icon;
    }

    public String getIconUpdateTime() {
        return iconUpdateTime;
    }

    public String getUvindex() {
        return uvindex;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public String getRainstormReminder() {
        return rainstormReminder;
    }

    public String getSpecialWxTips() {
        return specialWxTips;
    }

    public String getTxmessage() {
        return txmessage;
    }

    public String getMintempFrom00To09() {
        return mintempFrom00To09;
    }

    public String getRainfallFrom00To12() {
        return rainfallFrom00To12;
    }

    public String getRainfallLastMonth() {
        return rainfallLastMonth;
    }

    public String getRainfallJanuaryToLastMonth() {
        return rainfallJanuaryToLastMonth;
    }

    public Object getTemperature() {
        return temperature;
    }

    public Object getHumidity() {
        return humidity;
    }

    // Setters
    public void setLightning(String lightning) {
        this.lightning = lightning;
    }

    public void setRainfall(List<String> rainfall) {
        this.rainfall = rainfall;
    }

    public void setIcon(List<Integer> icon) {
        this.icon = icon;
    }

    public void setIconUpdateTime(String iconUpdateTime) {
        this.iconUpdateTime = iconUpdateTime;
    }

    public void setUvindex(String uvindex) {
        this.uvindex = uvindex;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public void setRainstormReminder(String rainstormReminder) {
        this.rainstormReminder = rainstormReminder;
    }

    public void setSpecialWxTips(String specialWxTips) {
        this.specialWxTips = specialWxTips;
    }

    public void setTxmessage(String txmessage) {
        this.txmessage = txmessage;
    }

    public void setMintempFrom00To09(String mintempFrom00To09) {
        this.mintempFrom00To09 = mintempFrom00To09;
    }

    public void setRainfallFrom00To12(String rainfallFrom00To12) {
        this.rainfallFrom00To12 = rainfallFrom00To12;
    }

    public void setRainfallLastMonth(String rainfallLastMonth) {
        this.rainfallLastMonth = rainfallLastMonth;
    }

    public void setRainfallJanuaryToLastMonth(String rainfallJanuaryToLastMonth) {
        this.rainfallJanuaryToLastMonth = rainfallJanuaryToLastMonth;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String toString() {
        return String.format("lightning:%s,rainfall:%s,icon:%s,iconUpdateTime:%s",
                getLightning(),
                getRainfall(),
                getIcon(),
                getIconUpdateTime()); // TODO: Complete whole data set w/ null checks
    }
}
