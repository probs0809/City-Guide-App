package com.javamini.TravelCompanion;

public class Hotel {
    public String PlaceWebsite;
    public String PlaceLocation;
    public String PlaceDescription;
    public String PlaceImageLink;
    public String PlaceName;
    public String PlaceMapID;

    Hotel(){

    }

    Hotel(String placeImageLink, String placeName, String placeDescription, String placeLocation, String placeWebsite, String PlaceMapID){
        PlaceName = placeName;
        PlaceImageLink = placeImageLink;
        PlaceDescription = placeDescription;
        PlaceLocation = placeLocation;
        PlaceWebsite = placeWebsite;
        this.PlaceMapID = PlaceMapID;
    }
}
