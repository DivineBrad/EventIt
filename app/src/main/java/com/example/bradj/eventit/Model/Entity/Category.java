package com.example.bradj.eventit.Model.Entity;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("catId")
    @Expose
    private Integer catId;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
