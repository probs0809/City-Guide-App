package com.javamini.TravelCompanion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PghActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        Intent i = getIntent();
        String location = i.getStringExtra("Location");

        final ArrayList<Place> places = new ArrayList<>();

        if(location.equals("Location")){
            places.add(new Place(R.drawable.milinium, R.string.park_one, R.string.info_park_one,
                    R.string.address_park_one, R.string.web_park_one,
                    "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0a4b03693325:0x2e0791297a581241?sa=X&amp;ved=0ahUKEwjIuYKWjIbVAhVSmbQKHe0NBT8Q9RcICzAA"));
            places.add(new Place(R.drawable.zoo, R.string.park_two, R.string.info_park_two,
                    R.string.address_park_two, R.string.web_park_two,
                    "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0baaf0c5bde5:0xea93c728c84c6d1f?sa=X&amp;ved=0ahUKEwifi9-yl4bVAhVDblAKHRBMBecQ9RcICjAA"));
            places.add(new Place(R.drawable.rabby, R.string.park_three, R.string.info_park_three,
                    R.string.address_park_three, R.string.web_park_three,
                    "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e75b914039a03:0xe28a509c444bd573?sa=X&amp;ved=0ahUKEwj-le2VmYbVAhXRI1AKHXiUCEoQ9RcICjAA"));
            places.add(new Place(R.drawable.maitama, R.string.park_four, R.string.info_park_four,
                    R.string.address_park_four, R.string.web_park_four,
                    "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0a43d8aeb74f:0x5a876909d7a5ff13?sa=X&amp;ved=0ahUKEwjn34nDmYbVAhUFblAKHYAvCdMQ9RcICjAA"));

        }
        else{
            mDatabase = FirebaseDatabase.getInstance().getReference("/"+location + "/Parks");
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        places.add(ds.getValue(Place.class));
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
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
