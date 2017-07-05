package com.lab42.maham.senseilocater;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SwitchCompat;
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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lab42.maham.senseilocater.MainNotificationFragment;
import com.lab42.maham.senseilocater.R;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GPSTracker gpss;
    int time = 5;
    Timer t;
    TimerTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Switch s = (Switch)findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    Log.i("chal" , "ra");
                    Log.d("chal" , "ra");
                    Log.e("chal" , "ra");

                    /*Intent i = new Intent();
                    i.setAction("MyBroadcast");
                    i.putExtra("value",100);
                    sendBroadcast(i);*/
                    t = new Timer();
                    task = new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                int i = 0;
                                @Override
                                public void run() {
                                    if (time > 0) {
                                        time -= 1;
                                        i++;
                                    }
                                    else {



                                        gpss = new GPSTracker(getApplicationContext());
                                        double latitude = 0;
                                        double longitude = 0;
                                        // check if GPS enabled
                                        if(gpss.canGetLocation()) {

                                            latitude = gpss.getLatitude();
                                            longitude = gpss.getLongitude();
                                        }
                                       // Toast.makeText(getApplicationContext(), latitude+"  "+longitude+" ", Toast.LENGTH_SHORT).show();
                                        Log.d("lat" , latitude+"");
                                        Log.d("long" , longitude+"");

                                        Log.i("lat" , latitude+"");
                                        Log.i("long" , longitude+"");

                                        Log.e("lat" , latitude+"");
                                        Log.e("long" , longitude+"");

                            /*mMap.addMarker(new MarkerOptions()
                                    .position(gps)
                                    .title("Current Location"));*/


                                    }
                                }
                            });
                        }
                    };
                    t.scheduleAtFixedRate(task, 0, 1000);
                }
                /*else{
                    Intent i = new Intent();
                    i.setAction("MyBroadcast2");
                    sendBroadcast(i);
                }*/

            }
        });

        Fragment f = new MainNotificationFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_notification,f);
        ft.commit();

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
        getMenuInflater().inflate(R.menu.notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment f = null;
        int id = item.getItemId();

        if (id == R.id.nav_editProfile) {
            // Handle the camera action
          //  Toast.makeText(getApplication(),"HELO",Toast.LENGTH_SHORT).show();
//            EditProfileFragment notification=new EditProfileFragment();
//            android.support.v4.app.FragmentTransaction fragmentTransection=getSupportFragmentManager().beginTransaction();
//            fragmentTransection.replace(R.id.fragmentcontainer_notification, notification);
//            fragmentTransection.commit();


            f = new EditProfileFragment();
        } else if (id == R.id.nav_Notification) {
//            Toast.makeText(getApplication(),"No notification to show",Toast.LENGTH_SHORT).show();
//            MainNotificationFragment notification=new MainNotificationFragment();
//            android.support.v4.app.FragmentTransaction fragmentTransection=getSupportFragmentManager().beginTransaction();
//            fragmentTransection.replace(R.id.fragmentcontainer_notification, notification);
//            fragmentTransection.commit();
//            Toast.makeText(getApplication(),"heyyyyyy",Toast.LENGTH_SHORT).show();
            f = new MainNotificationFragment();
       }

        else if(id == R.id.nav_logout){
            Intent i = new Intent(getApplicationContext(),activity_login.class);
            i.putExtra("key",1);
            startActivity(i);
        }


        if(f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_notification, f);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
