package com.example.bradj.eventit.Model.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bradley Blanchard on 2017-12-29.
 */

public class EventList {
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    public List<Event> getItems() {
        return events;
    }

    public void setItems(List<Event> items) {
        this.events = items;
    }
    public int getSize(){
        return events.size();
    }

}
