package com.example.bradj.eventit.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.Organization;
import com.example.bradj.eventit.Model.Entity.SubscribedOrg;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.OrganizationService;
import com.example.bradj.eventit.Model.Service.SubscribedOrgService;
import com.example.bradj.eventit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley Blanchard on 2018-01-08.
 */

public class OrganizationsAdapter extends RecyclerView.Adapter<OrganizationsAdapter.MyViewHolder> {


    private Context context;
    private List<Organization> dataList;

    private Activity activity;
    private SubscribedOrgService subscribedOrgService= ApiUtils.getSubscribedOrgService();

    public OrganizationsAdapter(Context context, List<Organization> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public OrganizationsAdapter(Activity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;
    }
    public void setDataList (List<Organization> list) {
        this.dataList=list;

    }


    @Override
    public OrganizationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.organization_cell,parent,false);
        return new OrganizationsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrganizationsAdapter.MyViewHolder holder, final int position) {
        holder.tvOrgDetail.setText(dataList.get(position).getName());
//        holder.btnSubOrg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SubscribedOrg subscribedOrg=new SubscribedOrg();
//                subscribedOrg.setOrg_id(Long.parseLong(""+dataList.get(position).getOrgId()));
//                subscribedOrg.setUser_id(Long.parseLong(""+dataList.get(position).getOrgId()));
//                subscribedOrg.setOrg_id(Long.parseLong(""+dataList.get(position).getOrgId()));
//
//                subscribedOrgService.addSubscribedOrg()
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvOrgDetail;
        public Button btnSubOrg;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrgDetail = (TextView) itemView.findViewById(R.id.tvOrgDetail);
            btnSubOrg = (Button) itemView.findViewById(R.id.btnSubscribeOrg);
        }
    }



}
