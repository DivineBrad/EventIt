package com.example.bradj.eventit.Model.Service;

import com.example.bradj.eventit.Model.DataAccess.RetrofitClient;

/**
 * Created by Bradley Blanchard on 2017-12-29.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://10.0.3.2:8080/";

    public static EventService getEventService() {
        return RetrofitClient.getClient(BASE_URL).create(EventService.class);
    }
}



