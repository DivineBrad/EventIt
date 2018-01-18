package com.example.bradj.eventit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bradj.eventit.Model.Adapter.EventsAdapter;
import com.example.bradj.eventit.Model.Adapter.RegisteredEventsAdapter;
import com.example.bradj.eventit.Model.Entity.DataTransfer;
import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.EventService;
import com.example.bradj.eventit.Model.Service.RegisteredEventService;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EventService mService;
    private RecyclerView recyclerView;
    private RegisteredEventsAdapter regEventsAdapter;
    private List<RegisteredEvent> regEventsList;
    private EventsAdapter dataAdapter;
    private List<Event> dataArrayList;
    private static final String TAG = "EventsFragment";
    private List<Event> filteredList;
    private String access = "all";
    private String category = "all";
    private RegisteredEventService regService;

    private OnFragmentInteractionListener mListener;

    public EventsFragment() {
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
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        //Handle Spiiners

        //Populate the spinner in the fragment
        Spinner spinnerAccess = (Spinner) rootView.findViewById(R.id.sp_event_access);
        Spinner spinnerCategory = (Spinner) rootView.findViewById(R.id.sp_event_category);
        filteredList = new ArrayList<>();
        //initialize main arraylist of items
        dataArrayList = new ArrayList<>();
       // regEventsList = new ArrayList<>();

        // Get reference to recyler view
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvEvents);
        // Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataAdapter = new EventsAdapter((AppCompatActivity)getActivity());
        recyclerView.setAdapter(dataAdapter);
        loadRegisteredEvents();

       // loadEvents();
        // For xml string array resource
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.event_access,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.event_categories,
                android.R.layout.simple_spinner_item);
        //
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerAccess.setAdapter(filterAdapter);
        spinnerCategory.setAdapter(categoryAdapter);

        //  Spinner event listeners
        // Access Spinner
        spinnerAccess.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                String selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                access = selected.toString();
                //int duration = Toast.LENGTH_SHORT;

//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();

                filterList(access, category);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                access="all";
            }
        });

        // Event Category Spinner

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                String selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                category = selected.toString();

//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
                filterList(access, category);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                category="all";
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



    private void loadEvents() {

        mService = ApiUtils.getEventService();
        Call<List<Event>> call = mService.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
               dataArrayList = response.body();
                Gson gson = new Gson();
                User currentUser = gson.fromJson(Prefs.getString("user object",""),User.class);

                regEventsList = DataTransfer.regEvents;
                for (Event ev : dataArrayList ){
                    for (RegisteredEvent regEv : regEventsList){
                        if (regEv.getEvent().getEventId()==ev.getEventId()){
                            ev.setRegistered(true);
                        }
                    }

                }



                dataAdapter.setDataList(dataArrayList);
                dataAdapter.notifyDataSetChanged();
                Log.e("JsonData", dataArrayList.get(0).getDescription());


            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }

    //Get Registered Events List

    private void loadRegisteredEvents() {

        regService = ApiUtils.getRegisteredEventService();
        Gson gson = new Gson();
        User currentUser = gson.fromJson(Prefs.getString("user object",""),User.class);

        Call<List<RegisteredEvent>> call = regService.getUserRegisteredEvents(currentUser.getUserId());
        call.enqueue(new Callback<List<RegisteredEvent>>() {
            @Override
            public void onResponse(Call<List<RegisteredEvent>> call, Response<List<RegisteredEvent>> response) {
                regEventsList = response.body();
                DataTransfer.regEvents=regEventsList;
                loadEvents();
                Log.e("JsonData", regEventsList.get(0).getDescription());


            }

            @Override
            public void onFailure(Call<List<RegisteredEvent>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                loadEvents();

            }
        });
    }

    // Filter the list except Registered Events
    private void filterList(final String access, final String category){
            if (dataArrayList.size()<1) {
                dataArrayList = new ArrayList<>();
                mService = ApiUtils.getEventService();

                    Call<List<Event>> call = mService.getEvents();
                    call.enqueue(new Callback<List<Event>>() {
                        @Override
                        public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                            dataArrayList = response.body();
                            if (access.equals("all") && category.equals("all")) {
                                //dataAdapter.setDataList(dataArrayList);
                                loadEvents();
                            } else {
                                filteredList.clear();
                                for (Event ev : dataArrayList) {
                                    if (ev.getCategory().getType().equals(category)
                                            && ev.getAccess().equals(access)) {
                                        filteredList.add(ev);
                                    }
                                }
                                dataAdapter.setDataList(filteredList);
                            }

                            dataAdapter.notifyDataSetChanged();
                            Log.e("JsonData2", dataArrayList.get(0).getDescription());


                        }

                        @Override
                        public void onFailure(Call<List<Event>> call, Throwable t) {
                            Log.e("Error", t.getMessage());

                        }
                    });

            }// End of if dataArraylist check condition

        else{
                    if (access.equals("all") && category.equals("all")) {
                        dataAdapter.setDataList(dataArrayList);
                        dataAdapter.notifyDataSetChanged();
                    } else {
                        filteredList.clear();
                        for (Event ev : dataArrayList) {
                            if (access.equals("all")) {
                                if (ev.getCategory().getType().equals(category)) {
                                    filteredList.add(ev);
                                }
                            } else if (category.equals("all")) {
                                if (ev.getAccess().equals(access)) {
                                    filteredList.add(ev);
                                } else {
                                    if (ev.getAccess().equals(access) && ev.getCategory().getType().equals(category))
                                        filteredList.add(ev);
                                }

                            }

                        }
                        dataAdapter.setDataList(filteredList);
                        dataAdapter.notifyDataSetChanged();
                    }

    }

}


}


