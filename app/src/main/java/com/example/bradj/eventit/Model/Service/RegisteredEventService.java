package com.example.bradj.eventit.Model.Service;

import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

public interface RegisteredEventService {
    @GET("api/registration")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<RegisteredEvent>> getRegisteredEvents();
    @GET("api/registration/user/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<RegisteredEvent>> getUserRegisteredEvents(@Path("id") long id);
    @POST("api/registration")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<RegisteredEvent> addRegisteredEvent(@Body RegisteredEvent regEvent);
    @DELETE("api/registration/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<RegisteredEvent> deleteRegisteredEvent(@Path ("id") long id);




}
