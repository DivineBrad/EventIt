package com.example.bradj.eventit.Model.Service;

import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Entity.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ajibd on 1/3/2018.
 */

public interface UserService {

//    @POST("/api/user")
//    @FormUrlEncoded
//    Call<User> savePost(@Field("userName") String userName,
//                        @Field("pass") String pass,
//                        @Field("email") String email,
//                        @Field("firstName") String firstName,
//                        @Field("lastName") String lastName
//                        );

    @POST("/api/user")
    Call<User> saveUser(@Body User user);


    @GET("/api/user")
    Call<UserResponse> getUsers();

    @GET("/api/user/{userId}")
    Call<User> getUser(@Path("userId") String userId);

    @POST("/api/user/login")
    Call<User> getLoginUser(@Body User user);

    @GET("/api/event")
    Call<List<Event>> getEvents();
}
