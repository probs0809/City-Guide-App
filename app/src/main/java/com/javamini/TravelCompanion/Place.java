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
    public String Website = "";
    public String Location = "";
    public String Description = "";
    public String ImageLink = "";
    public String Name = "";

    /**
     * Image resource ID for the place
     */
    public int mPlaceImageID = NOT_PROVIDED;

    private int mPlaceNameID;
    private int mPlaceDescriptionID = NOT_PROVIDED;
    private int mPlaceLocationID;
    private int mPlaceWebsiteID;
    public String Maplink;

    Place(){

    }

    Place(int placeImageID, int placeNameID, int placeDescriptionID, int placeLocationID, int placeWebsiteID, String PlaceMapID){

        mPlaceNameID = placeNameID;
        mPlaceImageID = placeImageID;
        mPlaceDescriptionID = placeDescriptionID;
        mPlaceLocationID = placeLocationID;
        mPlaceWebsiteID = placeWebsiteID;
        Maplink = PlaceMapID;
    }

    Place(String placeImageLink, String placeName, String placeDescription, String placeLocation, String placeWebsite, String PlaceMapID){

        Name = placeName;
        ImageLink = placeImageLink;
        Description = placeDescription;
        Location = placeLocation;
        Website = placeWebsite;
        Maplink = PlaceMapID;
    }

    Place(int placeNameID, int placeLocationID, int placeWebsiteID, String PlaceMapID) {

        mPlaceNameID = placeNameID;
        mPlaceLocationID = placeLocationID;
        mPlaceWebsiteID = placeWebsiteID;
        Maplink = PlaceMapID;
    }

    Place(String placeName, String placeLocation, String placeWebsite, String PlaceMapID){

        Name = placeName;
        Location = placeLocation;
        Website = placeWebsite;
        Maplink = PlaceMapID;
    }



    public int getPlaceNameID(){
        return mPlaceNameID;
    }
    public String getPlaceName(){
        return Name;
    }


    public int getPlaceImageID(){
        return mPlaceImageID;
    }
    public String getImageLink(){return ImageLink;}


    public int getPlaceDescriptionID(){
        return mPlaceDescriptionID;
    }
    public String getDescription(){return Description;}


    public int getPlaceLocationID() {
        return mPlaceLocationID;
    }
    public String getLocation(){return Location; }


    public int getPlaceWebsiteID() {
        return mPlaceWebsiteID;
    }
    public String getWebsite(){return Website; }


    public String getPlaceMapID(){
        return Maplink;
    }


    public boolean hasImage() {

        boolean bool = false;
        if(mPlaceImageID == NOT_PROVIDED && ImageLink.isEmpty()){
            bool = false;
        }else{
            bool = true;
        }
        return bool;
    }



    public boolean hasPlaceInfo() {
        boolean bool = false;
        if(mPlaceDescriptionID == NOT_PROVIDED && Description.isEmpty()){
            bool = false;
        }else{
            bool = true;
        }
        return bool;
    }

}

