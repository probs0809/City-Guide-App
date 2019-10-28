package com.javamini.TravelCompanion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_list);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.string.restaurants_one, R.string.address_restaurants_one,
                R.string.web_restaurants_one,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e74d864d944a1:0xa5055ddefacb3087?sa=X&amp;ved=0ahUKEwjy6tKtj4bVAhVLbVAKHZwuDn4Q9RcICjAA"));
        places.add(new Place(R.string.restaurants_two, R.string.address_restaurants_two,
                R.string.web_restaurants_two,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e74d864d944a1:0xa5055ddefacb3087?sa=X&amp;ved=0ahUKEwjy6tKtj4bVAhVLbVAKHZwuDn4Q9RcICjAA"));
        places.add(new Place(R.string.restaurants_three, R.string.address_restaurants_three,
                R.string.web_restaurants_three,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0ba15a7f33df:0xe615c7b3417c8517?sa=X&amp;ved=0ahUKEwi5ltfwkIbVAhWBZlAKHd0aBCwQ9RcICzAA"));
        places.add(new Place(R.string.restaurants_four, R.string.address_restaurants_four,
                R.string.web_restaurants_four,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0a7aa40a28f3:0x3bf5564547bd2086?sa=X&amp;ved=0ahUKEwjtxv3NkYbVAhXKLlAKHccCBgAQ9RcICzAA"));
        places.add(new Place(R.string.restaurants_five, R.string.address_restaurants_five,
                R.string.web_restaurants_five,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0af77d700c3d:0xf7276d9f2693caf9?sa=X&amp;ved=0ahUKEwiA0c75kYbVAhXBUlAKHZbBB0kQ9RcICzAA"));
        places.add(new Place(R.string.restaurants_six, R.string.address_restaurants_six,
                R.string.web_restaurants_six,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0af63a108f65:0xafbdefe2c2a1de69?sa=X&amp;ved=0ahUKEwiV-KiqkobVAhUJPVAKHblNDBcQ9RcICjAA"));
        places.add(new Place(R.string.restaurants_seven, R.string.address_restaurants_seven,
                R.string.web_restaurants_seven,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0bc2cf4c4615:0x7dc5e44200e3e8c4?sa=X&amp;ved=0ahUKEwjUq4nJkobVAhXNYVAKHYHKCosQ9RcICjAA"));
        places.add(new Place(R.string.restaurants_eight, R.string.address_restaurants_eight,
                R.string.web_restaurants_eight,
                "https://www.google.com.ng/maps/dir/\\'\\'/\\'\\'/data=!4m5!4m4!1m0!1m2!1m1!1s0x104e0a7ca409523f:0x18cc49b77c02f651?sa=X&amp;ved=0ahUKEwjt58PmkobVAhVMKFAKHeazASMQ9RcICzAA"));


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
