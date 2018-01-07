package com.example.bradj.eventit.Model.Entity;

/**
 * Created by ajibd on 1/6/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Category_ {

        @SerializedName("catId")
        @Expose
        private Integer catId;
        @SerializedName("type")
        @Expose
        private String type;

        /**
         * No args constructor for use in serialization
         *
         */
        public Category_() {
        }

        /**
         *
         * @param catId
         * @param type
         */
        public Category_(Integer catId, String type) {
            super();
            this.catId = catId;
            this.type = type;
        }

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

