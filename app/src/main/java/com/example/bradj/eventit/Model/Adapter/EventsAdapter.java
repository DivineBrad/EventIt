package com.example.bradj.eventit.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private enum enumType { EVENT, REGISTERED_EVENT };
    private Context context;
    private List<Event> dataList;
    private List<RegisteredEvent> regEventsList;
    private Activity activity;
    private String type=enumType.EVENT.name();


    public EventsAdapter(Context context, List<Event> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    public EventsAdapter(Activity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;

    }

    public EventsAdapter( List<Event> dataList) {

        this.dataList = dataList;
        this.type = enumType.EVENT.name();
    }

    public void setDataList (List<Event> list) {
        this.dataList=list;

    }
    public void setRegEventsList (List<RegisteredEvent> list) {
        this.regEventsList=list;
        this.type = enumType.REGISTERED_EVENT.name();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.MyViewHolder holder, int position) {
        if (type.equals(enumType.EVENT.name())){
            holder.tvEventDetail.setText(dataList.get(position).getName());
        }
        else {
            holder.tvEventDetail.setText(regEventsList.get(position).getEvent().getName());
        }



    }

    @Override
    public int getItemCount() {
        if (type.equals(enumType.EVENT.name())){
            return dataList.size();
        }
        else {
            return regEventsList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvEventDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvEventDetail = (TextView) itemView.findViewById(R.id.tvEventDetail);
        }
    }





}
