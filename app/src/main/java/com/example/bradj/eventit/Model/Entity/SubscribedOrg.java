package com.example.bradj.eventit.Model.Entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscribedOrg {

    @SerializedName("subscribedId")
    @Expose
    private Integer subscribedId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("organization")
    @Expose
    private Organization organization;

    public Integer getSubscribedId() {
        return subscribedId;
    }

    public void setSubscribedId(Integer subscribedId) {
        this.subscribedId = subscribedId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}