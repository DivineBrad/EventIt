package com.example.bradj.eventit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Adapter.EventsAdapter;
import com.example.bradj.eventit.Model.Adapter.RegisteredEventsAdapter;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.RegisteredEventService;
import com.example.bradj.eventit.Utilities.LoginUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisteredEventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisteredEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisteredEventsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RegisteredEventService mService;
    private RecyclerView recyclerView;
    private List<RegisteredEvent> regEventsList;
    private RegisteredEventsAdapter dataAdapter;
    private List<RegisteredEvent> dataArrayList=new ArrayList<RegisteredEvent>();
    private TextView statusText;


    private RegisteredEventsFragment.OnFragmentInteractionListener mListener;

    public RegisteredEventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisteredEventsFragment newInstance(String param1, String param2) {
        RegisteredEventsFragment fragment = new RegisteredEventsFragment();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_registered_event, container, false);

        //initialize main arraylist of items
        dataArrayList = new ArrayList<>();


        // Get reference to recyler view
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvRegisteredEvents);
        statusText=(TextView) rootView.findViewById(R.id.status_text);
        // Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataAdapter = new RegisteredEventsAdapter(getContext());
        recyclerView.setAdapter(dataAdapter);
        loadEvents();

        FloatingActionButton floatingActionButton=(FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadEvents();
            }
        });
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
        if (context instanceof RegisteredEventsFragment.OnFragmentInteractionListener) {
            mListener = (RegisteredEventsFragment.OnFragmentInteractionListener) context;
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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

    // Events API Call logic



    public void loadEvents() {

        int userId = getContext().getSharedPreferences(LoginUtil.PREFS_NAME,Context.MODE_PRIVATE).getInt("user id",0);

        mService = ApiUtils.getRegisteredEventService();
        Call<List<RegisteredEvent>> call = mService.getUserRegisteredEvents(userId);
        call.enqueue(new Callback<List<RegisteredEvent>>() {
            @Override
            public void onResponse(Call<List<RegisteredEvent>> call, Response<List<RegisteredEvent>> response) {
                if (response.isSuccessful()){
                    if(response.body()==null){
                        statusText.setText("You haven't registered for any events");
                        statusText.setVisibility(View.VISIBLE);
                    }
                    else{
                        dataArrayList = response.body();
                        dataAdapter.setDataList(dataArrayList);
                        dataAdapter.notifyDataSetChanged();
                        Log.e("JsonData", dataArrayList.get(0).getDescription());
                    }
                }



            }

            @Override
            public void onFailure(Call<List<RegisteredEvent>> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }





}
