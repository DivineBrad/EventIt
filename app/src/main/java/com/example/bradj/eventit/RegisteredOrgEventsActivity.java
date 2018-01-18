package com.example.bradj.eventit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bradj.eventit.Model.Adapter.EventsAdapter;
import com.example.bradj.eventit.Model.Adapter.RegisteredEventsAdapter;
import com.example.bradj.eventit.Model.Adapter.UserOrgEventsAdapter;
import com.example.bradj.eventit.Model.Entity.DataTransfer;
import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.EventService;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisteredOrgEventsActivity extends AppCompatActivity {
    private EventService mService;
    private RecyclerView recyclerView;
    private RegisteredEventsAdapter regEventsAdapter;
    private List<RegisteredEvent> regEventsList;
    private UserOrgEventsAdapter dataAdapter;
    private List<Event> dataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_org_events);
        dataArrayList = new ArrayList<>();


        // Get reference to recyler view
        recyclerView = (RecyclerView) findViewById(R.id.rvRegOrgEvents);
        // Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new UserOrgEventsAdapter(this);
        recyclerView.setAdapter(dataAdapter);
        loadEvents();
    }

    private void loadEvents() {

        mService = ApiUtils.getEventService();
        Call<List<Event>> call = mService.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                dataArrayList = response.body();
                dataAdapter.setDataList(dataArrayList);
                dataAdapter.notifyDataSetChanged();
                Log.e("JsonData", dataArrayList.get(0).getDescription());


            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }




}
