package com.example.bradj.eventit.Model.Service;

/**
 * Created by Bradley Blanchard on 2017-12-28.
 */

import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.EventList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface EventService {

    @GET("/api/event")
    Call<EventList> getEvents();

}
