package com.example.bradj.eventit.Model.Entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("userId")
    @Expose
    private long userId;
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

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param lastName
     * @param email
     * @param userName
     * @param firstName
     * @param pass
     */
    public User(String userName, String pass, String email, String firstName, String lastName) {
        super();
        this.userId=0;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int userId,String userName, String pass, String email, String firstName, String lastName) {
        super();
        this.userId=userId;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

}