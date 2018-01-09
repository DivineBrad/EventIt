package com.example.bradj.eventit.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.SubscribedOrg;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.SubscribedOrgService;
import com.example.bradj.eventit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bradley Blanchard on 2018-01-08.
 */

public class SubscribedOrgAdapter extends RecyclerView.Adapter<SubscribedOrgAdapter.MyViewHolder> {


    private Context context;
    private List<SubscribedOrg> dataList;

    private Activity activity;
    private SubscribedOrgService subscribedOrgService= ApiUtils.getSubscribedOrgService();

    public SubscribedOrgAdapter(Context context, List<SubscribedOrg> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public SubscribedOrgAdapter(Activity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;

    }

    public void setDataList(List<SubscribedOrg> list) {
        this.dataList = list;

    }


    @Override
    public SubscribedOrgAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscribed_org_cell, parent, false);
        return new SubscribedOrgAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscribedOrgAdapter.MyViewHolder holder, final int position) {
        holder.tvOrgDetail.setText(dataList.get(position).getOrganization().getName());
        holder.btnUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscribedOrgService.deleteSubscribedOrg(dataList.get(position).getSubscribedId()).enqueue(new Callback<SubscribedOrg>() {
                    @Override
                    public void onResponse(Call<SubscribedOrg> call, Response<SubscribedOrg> response) {
                        if (response.isSuccessful()){
                            dataList.remove(position);
                        }
                    }

                    @Override
                    public void onFailure(Call<SubscribedOrg> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvOrgDetail;
        public Button btnUnsub;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrgDetail = (TextView) itemView.findViewById(R.id.tvSubOrgDetail);
            btnUnsub=(Button) itemView.findViewById(R.id.btnUnSub_Org);
        }
    }
}

