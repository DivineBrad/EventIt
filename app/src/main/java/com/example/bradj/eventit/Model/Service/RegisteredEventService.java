package com.example.bradj.eventit.Model.Service;

import com.example.bradj.eventit.Model.Entity.RegisteredEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

public interface RegisteredEventService {
    @GET("api/registration")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<RegisteredEvent>> getRegisteredEvents();

}
