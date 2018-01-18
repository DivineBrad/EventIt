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
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Service.RegisteredEventService;
import com.example.bradj.eventit.R;
import com.example.bradj.eventit.RegisteredEventsFragment;
import com.example.bradj.eventit.ViewEventFragment;
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
 * Created by Bradley Blanchard on 2018-01-06.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder>  {

    private enum enumType { EVENT, REGISTERED_EVENT };
    private Context context;
    private List<Event> dataList;
    private List<RegisteredEvent> regEventsList;
    private Activity activity;
    private String type=enumType.EVENT.name();
    private RegisteredEventService regService;
    private static RegisteredEvent regEvent;
    public  Event event;


    public EventsAdapter(Context context, List<Event> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    public EventsAdapter(AppCompatActivity activity) {
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
    public void onBindViewHolder(EventsAdapter.MyViewHolder holder, final int position) {
        if (type.equals(enumType.EVENT.name())){
            holder.tvEventDetail.setText(dataList.get(position).getName());
        }
        else {
            holder.tvEventDetail.setText(regEventsList.get(position).getEvent().getName());
        }



//        if (!dataList.get(position).isUserSubscribed()){
//            holder.btnUnsub.setText("unsubscribe");
//            holder.btnUnsub.setEnabled(true);
//        }
//        else {
//            holder.btnUnsub.setText("unsubscribed");
//            holder.btnUnsub.setEnabled(false);
//        }
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataTransfer.event = dataList.get(position);
//                Gson gson = new Gson();
//                User currentUser = gson.fromJson(Prefs.getString("user object",""),User.class);
//                regEvent = new RegisteredEvent();
//                regEvent.setEvent(event);
//                regEvent.setUser(currentUser);
//                Date date = new Date();
//                String DATE_FORMAT = "MM/dd/yyyy";
//                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//                regEvent.setDate(sdf.format(date));
               // registerEvent(event);
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                ViewEventFragment viewEventFragment = new ViewEventFragment();
                FragmentTransaction fragmentTransaction =  manager.beginTransaction();
                fragmentTransaction.replace(R.id.container, viewEventFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                ///activity.setTitle(activity.iteitem.getTitle());


            }
        });



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
       public Button btnView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvEventDetail = (TextView) itemView.findViewById(R.id.tvEventDetail);
            btnView = (Button) itemView.findViewById(R.id.btnViewEvent);
        }
    }
    public void registerEvent (final Event event) {


        regService.addRegisteredEvent(regEvent).enqueue(new Callback<RegisteredEvent>() {

            @Override
            public void onResponse(Call<RegisteredEvent> call, Response<RegisteredEvent> response) {
                if(response.isSuccessful()){
                    Log.e("Sent properly", "Should work");
                    event.setRegistered(true);
                    notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(Call<RegisteredEvent> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });




        notifyDataSetChanged();

    }





}
