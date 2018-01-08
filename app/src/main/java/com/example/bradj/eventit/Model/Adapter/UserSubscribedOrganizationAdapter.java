package com.example.bradj.eventit.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.SubscribedOrg;
import com.example.bradj.eventit.Model.Service.SubscribedOrgService;
import com.example.bradj.eventit.R;
import com.example.bradj.eventit.UserSubscribedOrganizationsFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajibd on 1/7/2018.
 */

public class UserSubscribedOrganizationAdapter  extends RecyclerView.Adapter<UserSubscribedOrganizationAdapter.MyViewHolder> {

    private Context context;
    private List<SubscribedOrg> dataList;
    private Activity activity;
    private SubscribedOrgService subscribedOrgService;


    public UserSubscribedOrganizationAdapter(Context context, List<SubscribedOrg> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public UserSubscribedOrganizationAdapter(Activity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;
    }

    public UserSubscribedOrganizationAdapter(List<SubscribedOrg> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(List<SubscribedOrg> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UserSubscribedOrganizationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.organization_cell,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(UserSubscribedOrganizationAdapter.MyViewHolder holder, final int position) {
        Log.i("",dataList.get(position).getOrganization().getName());
        holder.tvUserSubscribed.setText(dataList.get(position).getOrganization().getName());
        final long id=dataList.get(position).getSubscribedId();
        holder.btnSubscribtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscribedOrgService.deleteSubscribedOrg(id).enqueue(new Callback<SubscribedOrg>() {
                    @Override
                    public void onResponse(Call<SubscribedOrg> call, Response<SubscribedOrg> response) {
                       // ((UserSubscribedOrganizationsFragment)this).loadJSON();

                        UserSubscribedOrganizationAdapter.this.dataList.remove(position);
                    }

                    @Override
                    public void onFailure(Call<SubscribedOrg> call, Throwable t) {

                    }
                });
            }
        });
        this.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvUserSubscribed ;
        public Button btnSubscribtion;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvUserSubscribed = (TextView) itemView.findViewById(R.id.user_subscribed_organization_name);
            btnSubscribtion = (Button) itemView.findViewById(R.id.user_subscribed_organization_btn);
        }
    }
}
