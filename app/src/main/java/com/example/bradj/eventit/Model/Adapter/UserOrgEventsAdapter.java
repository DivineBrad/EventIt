package com.example.bradj.eventit.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.DataTransfer;
import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Service.RegisteredEventService;
import com.example.bradj.eventit.R;
import com.example.bradj.eventit.ViewEventFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bradley Blanchard on 2018-01-18.
 */

public class UserOrgEventsAdapter extends RecyclerView.Adapter<UserOrgEventsAdapter.MyViewHolder>{


    private enum enumType { EVENT, REGISTERED_EVENT };
    private Context context;
    private List<Event> dataList;
    private List<RegisteredEvent> regEventsList;
    private Activity activity;
    
    private RegisteredEventService regService;
    private static RegisteredEvent regEvent;
    public  Event event;


    public UserOrgEventsAdapter(Context context, List<Event> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    public UserOrgEventsAdapter(AppCompatActivity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;

    }

    public UserOrgEventsAdapter( List<Event> dataList) {

        this.dataList = dataList;
        
    }

    public void setDataList (List<Event> list) {
        this.dataList=list;

    }


    @Override
    public UserOrgEventsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cell,parent,false);
        return new UserOrgEventsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserOrgEventsAdapter.MyViewHolder holder, final int position) {
        
            holder.tvEventDetail.setText(dataList.get(position).getName());
            
       
    }

    @Override
    public int getItemCount() {

            return dataList.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvEventDetail;
        public Button btnView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvEventDetail = (TextView) itemView.findViewById(R.id.tvEventDetail);
            btnView = (Button) itemView.findViewById(R.id.btnViewEvent);
        }
    }







}
