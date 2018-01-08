package com.example.bradj.eventit.Model.Service;

/**
 * Created by Bradley Blanchard on 2017-12-28.
 */

import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.EventList;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventService {

    // General Events calls
    @GET("/api/event")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<Event>> getEvents();

    @GET("/api/event/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<Event> getEvents(@Path("id") long id);



}
