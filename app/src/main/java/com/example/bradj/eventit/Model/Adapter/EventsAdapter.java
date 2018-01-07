package com.example.bradj.eventit.Model.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.R;

import java.util.List;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {


    private Context context;
    private List<Event> dataList;

    public EventsAdapter(Context context, List<Event> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    public EventsAdapter( List<Event> dataList) {

        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.MyViewHolder holder, int position) {
        holder.tvEventDetail.setText(dataList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvEventDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvEventDetail = (TextView) itemView.findViewById(R.id.tvEventDetail);
        }
    }





}
