package com.example.bradj.eventit.Model.Service;

import com.example.bradj.eventit.Model.Entity.Organization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Bradley Blanchard on 2018-01-08.
 */

public interface OrganizationService {

    @GET("api/organization")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<Organization>> getOrganizations();

    @POST("api/organization")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<Organization> addOrganization(@Body Organization regEvent);
    @DELETE("api/organization/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<Organization> deleteOrganization(@Path ("id") long id);


    
    
}
