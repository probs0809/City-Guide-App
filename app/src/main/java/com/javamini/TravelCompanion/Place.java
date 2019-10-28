package com.javamini.TravelCompanion;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Benjamin on 4/7/2017.
 * {@link Place} represents a location that a user can visit
 * it contains a String which is the name of the place.
 */

public class Place {

    /**
     * Constant value that represents no value provide for place
     */
    private static final int NOT_PROVIDED = -1;
    private String mPlaceWebsite;
    private String mPlaceLocation;
    private String mPlaceDescription;
    private String mPlaceImageLink;
    private String mPlaceName;

    /**
     * Image resource ID for the place
     */
    private int mPlaceImageID = NOT_PROVIDED;

    private int mPlaceNameID;
    private int mPlaceDescriptionID = NOT_PROVIDED;
    private int mPlaceLocationID;
    private int mPlaceWebsiteID;
    private String  mPlaceMapID;

    public Place(int placeImageID, int placeNameID, int placeDescriptionID, int placeLocationID, int placeWebsiteID, String PlaceMapID){

        mPlaceNameID = placeNameID;
        mPlaceImageID = placeImageID;
        mPlaceDescriptionID = placeDescriptionID;
        mPlaceLocationID = placeLocationID;
        mPlaceWebsiteID = placeWebsiteID;
        mPlaceMapID = PlaceMapID;
    }

    public Place(String placeImageLink, String placeName, String placeDescription, String placeLocation, String placeWebsite, String PlaceMapID){

        mPlaceName = placeName;
        mPlaceImageLink = placeImageLink;
        mPlaceDescription = placeDescription;
        mPlaceLocation = placeLocation;
        mPlaceWebsite = placeWebsite;
        mPlaceMapID = PlaceMapID;
    }

    public Place(int placeNameID, int placeLocationID, int placeWebsiteID, String PlaceMapID) {

        mPlaceNameID = placeNameID;
        mPlaceLocationID = placeLocationID;
        mPlaceWebsiteID = placeWebsiteID;
        mPlaceMapID = PlaceMapID;
    }

    public Place(String placeName, String placeLocation, String placeWebsite, String PlaceMapID){

        mPlaceName = placeName;
        mPlaceLocation = placeLocation;
        mPlaceWebsite = placeWebsite;
        mPlaceMapID = PlaceMapID;
    }



    public int getPlaceNameID(){
        return mPlaceNameID;
    }
    public String getPlaceName(){
        return mPlaceName;
    }


    public int getPlaceImageID(){
        return mPlaceImageID;
    }
    public String getmPlaceImageLink(){return mPlaceImageLink;}


    public int getPlaceDescriptionID(){
        return mPlaceDescriptionID;
    }
    public String getmPlaceDescription(){return mPlaceDescription;}


    public int getPlaceLocationID() {
        return mPlaceLocationID;
    }
    public String getmPlaceLocation(){return mPlaceLocation; }


    public int getPlaceWebsiteID() {
        return mPlaceWebsiteID;
    }
    public String getmPlaceWebsite(){return mPlaceWebsite; }


    public String getPlaceMapID(){
        return mPlaceMapID;
    }


    public boolean hasImage() {
        return (mPlaceImageID == NOT_PROVIDED || mPlaceImageLink.isEmpty())?FALSE:TRUE;
    }



    public boolean hasPlaceInfo() {
        return (mPlaceDescriptionID == NOT_PROVIDED || mPlaceDescription.isEmpty())?FALSE:TRUE;
    }

}

