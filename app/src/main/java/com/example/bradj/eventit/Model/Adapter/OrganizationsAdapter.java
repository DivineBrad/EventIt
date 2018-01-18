package com.example.bradj.eventit.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.Organization;
import com.example.bradj.eventit.Model.Entity.SubscribedOrg;
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.OrganizationService;
import com.example.bradj.eventit.Model.Service.SubscribedOrgService;
import com.example.bradj.eventit.OrganizationsFragment;
import com.example.bradj.eventit.R;
import com.example.bradj.eventit.SubscribedOrgFragment;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bradley Blanchard on 2018-01-08.
 */

public class OrganizationsAdapter extends RecyclerView.Adapter<OrganizationsAdapter.MyViewHolder> {


    private Context context;
    private List<Organization> dataList;
    private static SubscribedOrg subOrg;


    private Activity activity;
    private SubscribedOrgService subService= ApiUtils.getSubscribedOrgService();
    private OrganizationService organizationService = ApiUtils.getOrganizationService();

    public OrganizationsAdapter(Context context, List<Organization> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public OrganizationsAdapter(Activity activity,Context context) {
        this.dataList = new ArrayList<>();
        this.activity = activity;
        this.context=context;
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
        final User currentUser = new User();

        holder.tvOrgDetail.setText(dataList.get(position).getName());
        if (dataList.get(position).isSubscribed()){
            holder.btnSubOrg.setEnabled(false);
        }

        holder.btnSubOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Testing Org Click", "Subscribed Clicked");
                SubscribedOrg subscribedOrg=new SubscribedOrg();
                Organization org = new Organization();
                org.setOrgId(dataList.get(position).getOrgId());
                int userId=Prefs.getInt("user_id",1);
                Gson gson = new Gson();
                User currentUser = gson.fromJson(Prefs.getString("user object",""),User.class);
                // Get current time
                Date date = new Date();
                String DATE_FORMAT = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
               // System.out.println("Today is " + sdf.format(date));
                subscribedOrg.setDate(sdf.format(date));
                subscribedOrg.setUser(currentUser);
                subscribedOrg.setOrganization(org);
                OrganizationsAdapter.subOrg=subscribedOrg;
               // Add to db through api call
               // dataList.get(position).setSubscribed(true);
                addSubscribedOrg(dataList.get(position));

            }
        });
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

    private void addSubscribedOrg (final Organization org){


         subService.addSubscribedOrg(OrganizationsAdapter.subOrg).enqueue(new Callback<SubscribedOrg>() {

            @Override
            public void onResponse(Call<SubscribedOrg> call, Response<SubscribedOrg> response) {
                if(response.isSuccessful()){
                    Log.e("Sent properly", "Should work");
                    org.setSubscribed(true);
                   notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(Call<SubscribedOrg> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }




}
