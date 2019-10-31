package com.javamini.TravelCompanion;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //String[] location = {"India","USA"};
    ArrayList<String> location = new ArrayList<String>();
    int locationInt = 0;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Sets onClick Listener on the Hotel TextView to Launch the HotelsActivity
        TextView tvHotels = (TextView) findViewById(R.id.tv_hotels);
        tvHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHotels();
            }
        });

        // Sets onClick Listener on the Parks TextView to Launch the PghActivity
        TextView tvParks = (TextView) findViewById(R.id.tv_parks);
        tvParks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParks();
            }
        });

        // Sets onClick Listener on the Restaurants TextView to Launch the RestaurantsActivity
        TextView tvRestaurants = (TextView) findViewById(R.id.tv_restaurants);
        tvRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRestaurants();
            }
        });

        // Sets onClick Listener on the Tourist TextView to Launch the TouristsActivity
        TextView tvTourists = (TextView) findViewById(R.id.tv_tourist);
        tvTourists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTourists();
            }
        });



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
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        mDatabase = FirebaseDatabase.getInstance().getReference("/Location");
        location.add("Location");


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    location.add(ds.getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,location);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
        //spinner.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                locationInt = i;
               // Toast.makeText(getApplicationContext(),locationInt , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
        } else if (id == R.id.mn_hotels) {
            startHotels();
        } else if (id == R.id.mn_parks) {
            startParks();
        } else if (id == R.id.mn_restaurants) {
            startRestaurants();
        } else if (id == R.id.mn_tourist) {
            startTourists();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hotels) {
            startHotels();
        } else if (id == R.id.nav_parks) {
            startParks();

        } else if (id == R.id.nav_restaurants) {
            startRestaurants();

        } else if (id == R.id.nav_tourist) {
            startTourists();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    // Starts the Hotel Activity
    public void startHotels() {
        Intent intent = new Intent(this, HotelsActivity.class);
        intent.putExtra("Location",location.get(locationInt));
        startActivity(intent);
    }

    //Starts the pg Activity
    public void startParks() {
        Intent intent = new Intent(this, PghActivity.class);
        intent.putExtra("Location",location.get(locationInt));
        startActivity(intent);
    }

    // Starts the Restaurants Activity
    public void startRestaurants() {
        Intent intent = new Intent(this, RestaurantsActivity.class);
        intent.putExtra("Location",location.get(locationInt));
        startActivity(intent);
    }

    // Starts the Tourist Activity
    public void startTourists() {
        Intent intent = new Intent(this, TouristsActivity.class);
        intent.putExtra("Location",location.get(locationInt));
        startActivity(intent);
    }



}
