package com.example.bradj.eventit.Model.Entity;

/**
 * Created by ajibd on 1/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Organization implements Serializable {

    @SerializedName("orgId")
    @Expose
    private long orgId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("user")
    @Expose
    private User user;
    private boolean subscribed;

    /**
     * No args constructor for use in serialization
     */
    public Organization() {
    }

    /**
     * @param orgId
     * @param category
     * @param address
     * @param name
     * @param user
     */
    public Organization(Long orgId, String name, Address address, Category category, User user) {
        super();
        this.orgId = orgId;
        this.name = name;
        this.address = address;
        this.category = category;
        this.user = user;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}
