package com.example.bradj.eventit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.DataTransfer;
import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Entity.SubscribedOrg;
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.EventService;
import com.example.bradj.eventit.Model.Service.RegisteredEventService;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewEventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewEventFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Event event;
    private RegisteredEventService regEventService= ApiUtils.getRegisteredEventService();
    private  Button btnRegister;
    private RegisteredEvent regEvent;

    private OnFragmentInteractionListener mListener;

    public ViewEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewEventFragment newInstance(String param1, String param2) {
        ViewEventFragment fragment = new ViewEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        event= DataTransfer.event;
       // regEvent = new RegisteredEvent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_event, container, false);

        // Set event data in text views
        ((TextView) rootView.findViewById(R.id.txtDate)).setText(event.getDate());
        ((TextView) rootView.findViewById(R.id.txtName)).setText(event.getName());
        ((TextView) rootView.findViewById(R.id.txtDesc)).setText(event.getDescription());
        String address = event.getAddress().getStreet()+", "+
                event.getAddress().getCity()+", "+event.getAddress().getSubdivision()+ ", "+
                event.getAddress().getCode();
        String orgName = event.getOrganization().getName();
        ((TextView) rootView.findViewById(R.id.txtAddress)).setText(address);
        ((TextView) rootView.findViewById(R.id.txtOrg)).setText(orgName);


        //Set on click events
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);
       // check if private
        if (event.getAccess().equals("private")){
            btnRegister.setVisibility(View.VISIBLE);
        }
        else {
            btnRegister.setVisibility(View.INVISIBLE);
        }
        Gson gson = new Gson();
        User currentUser = gson.fromJson(Prefs.getString("user object",""),User.class);
        for (RegisteredEvent regEv : DataTransfer.regEvents){
            if (event.getEventId()==regEv.getEvent().getEventId()){
                btnRegister.setEnabled(false);
            }
        }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call method which uses api to register for this event
                regEvent= new RegisteredEvent();
                registerEvent(regEvent);
            }
        });

        // txtDate.setText(event.getDate());
        // Inflate the layout for this fragment
        return rootView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void registerEvent (final RegisteredEvent registeredEvent){
      //  regEvent = new RegisteredEvent();
        Log.e("Test", "Inside Register Event");
        Gson gson = new Gson();
        User currentUser = gson.fromJson(Prefs.getString("user object",""),User.class);
        Date date = new Date();
        String DATE_FORMAT = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        registeredEvent.setDate(sdf.format(date));
        registeredEvent.setEvent(event);

        registeredEvent.setUser(currentUser);
        Log.e("RegEventData", registeredEvent.getEvent().getName());
        regEventService.addRegisteredEvent(registeredEvent).enqueue(new Callback<RegisteredEvent>() {
            @Override
            public void onResponse(Call<RegisteredEvent> call, Response<RegisteredEvent> response) {
                if (response.isSuccessful()){

                btnRegister.setText("Registered");
                btnRegister.setEnabled(false);

                }
            }

            @Override
            public void onFailure(Call<RegisteredEvent> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });


    }
}
