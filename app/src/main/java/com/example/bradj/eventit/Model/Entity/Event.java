package com.example.bradj.eventit.Model.Entity;

/**
 * Created by Bradley Blanchard on 2017-12-28.
 */
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.io.Serializable;

public class Event implements Serializable{

    @SerializedName("eventId")
    @Expose
    private Long eventId;
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
    private Organization organization;
    @SerializedName("reglimit")
    @Expose
    private Integer reglimit;
    @SerializedName("access")
    @Expose
    private String access;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("category")
    @Expose
    private Category category;


    /**
     * No args constructor for use in serialization
     *
     */
    public Event() {
    }

    /**
     *
     * @param category
     * @param organization
     * @param reglimit
     * @param eventId
     * @param address
     * @param token
     * @param description
     * @param name
     * @param regLimit
     * @param access
     * @param date
     */
    public Event(Long eventId, String name, String token, String description, String date, Integer reglimit, String access, Organization organization, Address address, Category category, Integer regLimit) {
        super();
        this.eventId = eventId;
        this.name = name;
        this.token = token;
        this.description = description;
        this.date = date;
        this.reglimit = reglimit;
        this.access = access;
        this.organization = organization;
        this.address = address;
        this.category = category;
        this.reglimit = reglimit;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
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

    public Integer getReglimit() {
        return reglimit;
    }

    public void setReglimit(Integer reglimit) {
        this.reglimit = reglimit;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

     public  Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}


