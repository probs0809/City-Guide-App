package com.javamini.TravelCompanion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class TouristsActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    ArrayList<Place> places = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        Intent i = getIntent();
        String location = i.getStringExtra("Location");



        if(location.equals("Location")){
            places.add(new Place(R.drawable.mosque,R.string.tourist_one,R.string.info_tourist_one,
                    R.string.address_tourist_one,R.string.web_tourist_one,
                    "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0baf00555353:0x306c5459fc30896d?sa=X&amp;ved=0ahUKEwi9rt32h4bVAhUCmrQKHchhA8kQ9RcICzAA "));
            places.add(new Place(R.drawable.zuma,R.string.tourist_two,R.string.info_tourist_two,
                    R.string.address_tourist_two,R.string.web_tourist_two,
                    "https://www.google.com.ng/maps/place/Zuma+Rock/data=!4m2!3m1!1s0x104dd7d112caed1b:0xb415df4f7772bdb8!5m1!1e4?sa=X&amp;ved=0ahUKEwj089D9jobVAhWEEVAKHZ5oD3gQ8gEIfzAP"));
            places.add(new Place(R.drawable.aso,R.string.tourist_three,R.string.info_tourist_three,
                    R.string.address_tourist_three,R.string.web_tourist_three,
                    "https://www.google.com.ng/maps/place/Aso+Rock/@9.083333,7.5339223,17z/data=!3m1!4b1!4m5!3m4!1s0x104e0991e66fddfb:0x9d4da92741f9018!8m2!3d9.083333!4d7.536111!5m1!1e4"));
            places.add(new Place(R.drawable.stadium,R.string.tourist_four,R.string.info_tourist_four,
                    R.string.address_tourist_four,R.string.web_tourist_four,
                    "https://www.google.com.ng/maps/dir/\\'\\'/Location+of+the+national+stadium+abuja/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0b5a334275eb:0x3a912395bd8b3a54?sa=X&amp;ved=0ahUKEwjjmuXojYbVAhVObVAKHaDICOcQ9RcIjgEwDA"));


        }else{
//            mDatabase = FirebaseDatabase.getInstance().getReference("/"+location + "/TouristActivities");
//            mDatabase.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for(DataSnapshot ds : dataSnapshot.getChildren()){
//                        PlaceFirebase pf = ds.getValue(PlaceFirebase.class);
//                        places.add(new Place(pf.ImageLink,pf.Name,pf.Description,pf.Website,pf.Location,pf.Maplink));
//                        Toast.makeText(getApplicationContext(), pf.getImageLink(),Toast.LENGTH_LONG).show();
//                    }
//                }
//                @Override
//                public void onCancelled( DatabaseError databaseError) {
//
//                }
//            });




        }

        PlaceAdapter adapter = new PlaceAdapter(this, places, R.color.colorHome);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // get the place map id an launch a google map view of the place
                Place place = places.get(i);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(place.getPlaceMapID()));

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "No App to Handle Intent", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
