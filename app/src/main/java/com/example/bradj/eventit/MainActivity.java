package com.example.bradj.eventit;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Adapter.EventsAdapter;
import com.example.bradj.eventit.Model.Adapter.RegisteredEventsAdapter;
import com.example.bradj.eventit.Model.Entity.Event;
import com.example.bradj.eventit.Model.Entity.RegisteredEvent;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.EventService;
import com.example.bradj.eventit.Model.Service.RegisteredEventService;
import com.example.bradj.eventit.Model.Entity.User;
import com.example.bradj.eventit.Model.Service.ApiUtils;
import com.example.bradj.eventit.Model.Service.UserService;
import com.example.bradj.eventit.Utilities.LoginUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DashboardFragment.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener,
        EventsFragment.OnFragmentInteractionListener,
        RegisteredEventsFragment.OnFragmentInteractionListener,SubscribedOrgFragment.OnFragmentInteractionListener,
        OrganizationsFragment.OnFragmentInteractionListener{

    private LoginUtil loginUtil;
    private EventService mService;
    private RecyclerView recyclerView;
   // private RegisteredEventsAdapter dataAdapter;
    //private List<RegisteredEvent> dataArrayList;
    private List<Event> eventList;

    private User user;
    private UserService userService;
    public static final String PREFS_NAME = "default_preferences";
    private TextView userDetails;
    private TextView userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginUtil= LoginUtil.getInstance();
        userService= ApiUtils.getUserService();
        int userId=getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getInt("user id",0);
        user=new User();
        userService.getUser(""+userId).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    MainActivity.this.user=response.body();
                    userDetails=findViewById(R.id.display_userInfo);
                    userEmail=findViewById(R.id.display_userEmail);

                    userDetails.setText(user.getFirstName()+" "+user.getLastName());
                    userEmail.setText(user.getEmail());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentTransaction fragmentTransaction;

        DashboardFragment dFragment=DashboardFragment.newInstance("a","b");
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, dFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_settings:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
            }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer;
        FragmentTransaction fragmentTransaction;
        // Switch statement to handle menu options
        switch(id){
            case  R.id.dashboard:
                DashboardFragment dFragment=DashboardFragment.newInstance("a","b");
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, dFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setTitle(item.getTitle());
                break;

            case  R.id.events:
                EventsFragment eFragment=EventsFragment.newInstance("a","b");
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, eFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setTitle(item.getTitle());
                break;
            case  R.id.regEvents:
                RegisteredEventsFragment reFragment=RegisteredEventsFragment.newInstance("a","b");
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, reFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setTitle(item.getTitle());
                break;
            case  R.id.organizations:
              SubscribedOrgFragment oFragment=SubscribedOrgFragment.newInstance("a","b");
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, oFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setTitle(item.getTitle());
                break;

        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }

    public void logOut(MenuItem item) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        Log.i("info","Logout clicked");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.logout_confirm)
                .setTitle(R.string.log_out);

        builder.setPositiveButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                loginUtil.setLoggedIn(getApplicationContext(),false);
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
// Set other dialog properties

// Create the AlertDialog

// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        builder.show();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public void testTab(MenuItem item) {
        Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }
}
