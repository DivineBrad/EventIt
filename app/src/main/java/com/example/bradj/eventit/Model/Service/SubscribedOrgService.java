package com.example.bradj.eventit.Model.Service;

import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Entity.SubscribedOrg;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Bradley Blanchard on 2018-01-07.
 */

public interface SubscribedOrgService {

    @GET("/api/subscribed-org")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<SubscribedOrg>> getSubscribedOrgs();


    @GET("/api/subscribed-org/user/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<SubscribedOrg>> getUserSubscribedOrgs(@Path ("id") long id);

    @DELETE("/api/subscribed-org/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<SubscribedOrg> deleteSubscribedOrg(@Path("id") long id);

    @POST("/api/subscribed-org")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<SubscribedOrg> addSubscribedOrg(@Body SubscribedOrg sOrg);
}
