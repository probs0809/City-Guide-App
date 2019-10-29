package com.javamini.TravelCompanion;

import android.content.Context;
import androidx.core.content.ContextCompat;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by probs0809.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    private int mColorResourceId;

    public PlaceAdapter(Context context, ArrayList<Place> places, int colorResourceID) {
        super(context, 0, places);
        mColorResourceId = colorResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Place currentPlace = getItem(position);

        ImageView ivPlaceImage = (ImageView)listItemView.findViewById(R.id.iv_place_image);

        if (currentPlace.hasImage()) {
            if(currentPlace.getImageLink().isEmpty()){
                ivPlaceImage.setImageResource(currentPlace.getPlaceImageID());
            }else{
                ivPlaceImage.setImageURI(Uri.parse(currentPlace.getImageLink()));
            }

        } else {
            ivPlaceImage.setVisibility(View.GONE);
        }

        TextView tvPlaceName = (TextView) listItemView.findViewById(R.id.tv_place_name);

        if (currentPlace.getPlaceName().isEmpty()){
            tvPlaceName.setText(currentPlace.getPlaceNameID());
        }else{
            tvPlaceName.setText(currentPlace.getPlaceName());
        }

        TextView tvPlaceDescription = (TextView)listItemView.findViewById(R.id.tv_place_discription);

        if (currentPlace.hasPlaceInfo()) {
            if(currentPlace.getDescription().isEmpty()) {
                tvPlaceDescription.setText(currentPlace.getPlaceDescriptionID());
            }
            else{
                tvPlaceDescription.setText(currentPlace.getDescription());
            }
        } else {
            tvPlaceDescription.setVisibility(View.GONE);
        }

        TextView tvPlaceLocation = (TextView)listItemView.findViewById(R.id.tv_place_location);

        tvPlaceLocation.setText(currentPlace.getPlaceLocationID());

        TextView tvPlaceWebsite = (TextView)listItemView.findViewById(R.id.tv_place_website);

        if (currentPlace.getWebsite().isEmpty()) {
            tvPlaceWebsite.setText(currentPlace.getPlaceWebsiteID());
        }else{
            tvPlaceLocation.setText(currentPlace.getWebsite());
        }

        View textContainer = listItemView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
