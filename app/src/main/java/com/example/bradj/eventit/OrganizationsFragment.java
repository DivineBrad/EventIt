package com.example.bradj.eventit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bradj.eventit.Model.Adapter.OrganizationsAdapter;
import com.example.bradj.eventit.Model.Entity.Organization;
import com.example.bradj.eventit.Model.Entity.SubscribedOrg;
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.OrganizationService;
import com.example.bradj.eventit.Model.Service.SubscribedOrgService;
import com.example.bradj.eventit.Utilities.LoginUtil;
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
 * {@link OrganizationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrganizationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganizationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //My params
    private OrganizationService mService;
    private SubscribedOrgService subService;
    private RecyclerView recyclerView;
    private OrganizationsAdapter dataAdapter;
    private List<Organization> dataArrayList;
    private List <SubscribedOrg>  subscribedOrgList;



    private OnFragmentInteractionListener mListener;

    public OrganizationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrganizationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrganizationsFragment newInstance(String param1, String param2) {
        OrganizationsFragment fragment = new OrganizationsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_organizations, container, false);

        //initialize main arraylist of items
        dataArrayList = new ArrayList<>();


        // Get reference to recyler view
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvOrganizations);
        // Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataAdapter = new OrganizationsAdapter(getActivity(),this.getContext());
        recyclerView.setAdapter(dataAdapter);
       // loadOrgs();
        loadUserSubscribedList();


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

    private void loadOrgs() {
      //  int userId = getContext().getSharedPreferences(LoginUtil.PREFS_NAME,Context.MODE_PRIVATE).getInt("user id",0);

        mService = ApiUtils.getOrganizationService();
        Call<List<Organization>> call = mService.getOrganizations();
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                dataArrayList = response.body();
                List<Organization> newList = new ArrayList<Organization>();
                if (subscribedOrgList!=null){
                    for (Organization org : dataArrayList){
                        for (SubscribedOrg subOrg : subscribedOrgList){
                            if(org.getOrgId()==subOrg.getOrganization().getOrgId()){
                                org.setSubscribed(true);
                                Log.e("JsonData", Boolean.toString(org.isSubscribed()));
                            }
                        }
                    }
                }

                dataAdapter.setDataList(dataArrayList);
                dataAdapter.notifyDataSetChanged();
                Log.e("JsonData", dataArrayList.get(0).getName());


            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }
    // Add user id param
    private void loadSubscribedOrgs (){
        subService = ApiUtils.getSubscribedOrgService();

        Call<List<SubscribedOrg>> call = subService.getUserSubscribedOrgs(1);
        call.enqueue(new Callback<List<SubscribedOrg>>() {
            @Override
            public void onResponse(Call<List<SubscribedOrg>> call, Response<List<SubscribedOrg>> response) {
                subscribedOrgList = response.body();
                dataAdapter.setDataList(dataArrayList);
                dataAdapter.notifyDataSetChanged();
                Log.e("JsonData", dataArrayList.get(0).getName());


            }

            @Override
            public void onFailure(Call<List<SubscribedOrg>> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }
    private void loadUserSubscribedList (){
        subService = ApiUtils.getSubscribedOrgService();
        Gson gson = new Gson();
        User currentUser = gson.fromJson(Prefs.getString("user object",""),User.class);

        Call<List<SubscribedOrg>> call = subService.getUserSubscribedOrgs(currentUser.getUserId());
        call.enqueue(new Callback<List<SubscribedOrg>>() {
            @Override
            public void onResponse(Call<List<SubscribedOrg>> call, Response<List<SubscribedOrg>> response) {
                subscribedOrgList = response.body();
                loadOrgs();


            }

            @Override
            public void onFailure(Call<List<SubscribedOrg>> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });
    }

}
