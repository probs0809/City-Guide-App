package com.javamini.TravelCompanion;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

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
                new DownloadImageFromInternet(ivPlaceImage).execute(currentPlace.ImageLink);
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

        if(currentPlace.getLocation().equals("")){
            tvPlaceLocation.setText(currentPlace.getPlaceLocationID());
        }else{
            tvPlaceLocation.setText(currentPlace.getLocation());
        }





        TextView tvPlaceWebsite = (TextView)listItemView.findViewById(R.id.tv_place_website);

        if (currentPlace.getWebsite().equals("")) {
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

class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    @SuppressLint("RestrictedApi")
    public DownloadImageFromInternet(ImageView imageView) {
        this.imageView = imageView;
        Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
    }

    protected Bitmap doInBackground(String... urls) {
        String imageURL = urls[0];
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bimage = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
