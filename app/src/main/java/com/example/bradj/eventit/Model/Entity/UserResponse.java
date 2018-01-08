package com.example.bradj.eventit.Model.Entity;

/**
 * Created by ajibd on 1/3/2018.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse implements Serializable {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("alerts")
    @Expose
    private List<Object> alerts = null;
    @SerializedName("registrations")
    @Expose
    private List<Object> registrations = null;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public List<Object> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Object> alerts) {
        this.alerts = alerts;
    }

    public List<Object> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Object> registrations) {
        this.registrations = registrations;
    }

}
