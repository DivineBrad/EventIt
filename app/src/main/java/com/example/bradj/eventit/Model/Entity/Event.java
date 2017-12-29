package com.example.bradj.eventit.Model.Entity;

/**
 * Created by Bradley Blanchard on 2017-12-28.
 */
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("eventId")
    @Expose
    private long eventId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("organization")
    @Expose
    private Object organization;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("alerts")
    @Expose
    private Object alerts;
    @SerializedName("registrations")
    @Expose
    private Object registrations;


    public long getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getOrganization() {
        return organization;
    }

    public void setOrganization(Object organization) {
        this.organization = organization;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getAlerts() {
        return alerts;
    }

    public void setAlerts(Object alerts) {
        this.alerts = alerts;
    }

    public Object getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Object registrations) {
        this.registrations = registrations;
    }


}


