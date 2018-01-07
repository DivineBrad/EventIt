package com.example.bradj.eventit.Model.Service;

/**
 * Created by Bradley Blanchard on 2017-12-28.
 */

import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.EventList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface EventService {

    @GET("/api/event")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<Event>> getEvents();

}
