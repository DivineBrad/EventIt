package com.example.bradj.eventit.Model.Entity;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisteredEvent implements Serializable{

    @SerializedName("regId")
    @Expose
    private Integer regId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("event")
    @Expose
    private Event event;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("regLimit")
    @Expose
    private Integer regLimit;
    @SerializedName("checkedin")
    @Expose
    private Integer checkedin;

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getRegLimit() {
        return regLimit;
    }

    public void setRegLimit(Integer regLimit) {
        this.regLimit = regLimit;
    }

    public Integer getCheckedin() {
        return checkedin;
    }

    public void setCheckedin(Integer checkedin) {
        this.checkedin = checkedin;
    }
}