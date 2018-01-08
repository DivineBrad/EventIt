package com.example.bradj.eventit.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bradj.eventit.CheckInActivity;
import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.R;
import com.example.bradj.eventit.RegisteredEventsFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

public class RegisteredEventsAdapter extends RecyclerView.Adapter<RegisteredEventsAdapter.MyViewHolder>{

    private Context context;
    private List<RegisteredEvent> dataList;

    private Activity activity;

    public RegisteredEventsAdapter(Context context, List<RegisteredEvent> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public RegisteredEventsAdapter(Context context) {
        this.dataList = new ArrayList<>();
        this.context = context;

    }
    public void setDataList (List<RegisteredEvent> list) {
        this.dataList=list;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_reg_cell,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvEventDetail.setText(dataList.get(position).getEvent().getName());
        final RegisteredEvent event=dataList.get(position);
        holder.btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, CheckInActivity.class);
                intent.putExtra("event",event.getRegId());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvEventDetail;
        public Button btnCheckIn;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvEventDetail = (TextView) itemView.findViewById(R.id.tvRegEventDetail);
            btnCheckIn=(Button)itemView.findViewById(R.id.btnCheckIn);
        }
    }



}
