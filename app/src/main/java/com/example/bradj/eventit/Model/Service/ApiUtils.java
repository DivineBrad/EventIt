package com.example.bradj.eventit.Model.Service;

import com.example.bradj.eventit.Model.DataAccess.RetrofitClient;
import com.example.bradj.eventit.Model.Entity.SubscribedOrg;

/**
 * Created by Bradley Blanchard on 2017-12-29.
 */

public class ApiUtils {

    public static final String BASE_URL = " http://52.60.167.57:8080/";

    public static EventService getEventService() {
        return RetrofitClient.getClient(BASE_URL).create(EventService.class);
    }
    public static RegisteredEventService getRegisteredEventService() {
        return RetrofitClient.getClient(BASE_URL).create(RegisteredEventService.class);
    }

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    public static SubscribedOrgService getSubscribedOrgService() {
        return RetrofitClient.getClient(BASE_URL).create(SubscribedOrgService.class);
    }

}



