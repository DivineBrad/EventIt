package com.example.bradj.eventit.Model.Entity;

/**
 * Created by ajibd on 1/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address_ {

    @SerializedName("addressId")
    @Expose
    private Integer addressId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("subdivision")
    @Expose
    private String subdivision;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public Address_() {
    }

    /**
     *
     * @param subdivision
     * @param street
     * @param longitude
     * @param latitude
     * @param code
     * @param addressId
     * @param city
     */
    public Address_(Integer addressId, String city, String street, String subdivision, String code, String longitude, String latitude) {
        super();
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.subdivision = subdivision;
        this.code = code;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
