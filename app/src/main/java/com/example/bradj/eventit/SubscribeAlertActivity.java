package com.example.bradj.eventit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.bradj.eventit.Model.Adapter.SubscribeAlertsAdapter;
import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.EventList;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.EventService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeAlertActivity extends AppCompatActivity {
    private SubscribeAlertsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EventService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_alert);
        mService = ApiUtils.getEventService();
        mRecyclerView = (RecyclerView) findViewById(R.id.rvSubscribeAlerts);
        mAdapter = new SubscribeAlertsAdapter(this, new ArrayList<Event>(0), new SubscribeAlertsAdapter.EventItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(SubscribeAlertActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

       // loadAnswers();
    }

//    public void loadAnswers() {
//        mService.getEvents().enqueue(new Callback<List<Event>>() {
//            @Override
//            public void onResponse(Call<EventList> call, Response<EventList> response) {
//
//                if (response.isSuccessful()) {
//                    mAdapter.updateEvents(response.body().getItems());
//                    Log.d("SubscriberActivity", "events loaded from API");
//                } else {
//                    int statusCode = response.code();
//                    // handle request errors depending on status code
//                }
//            }
//
//            @Override
//            public void onFailure(Call<EventList> call, Throwable t) {
//                showErrorMessage();
//                Log.d("MainActivity", "error loading from API");
//
//            }
//            public  void showErrorMessage(){
//
//            }
//        });
//

    //}
}
