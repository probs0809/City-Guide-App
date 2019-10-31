package com.javamini.TravelCompanion;

public class PlaceFirebase {
     String Website ;
     String Location ;
     String Description ;
     String ImageLink ;
     String Name ;
     String Maplink ;

    PlaceFirebase(){

    }

    PlaceFirebase(String ImageLink, String Name, String Description, String Location, String Website, String Maplink){

        this.setName(Name);
        this.setImageLink(ImageLink);
        this.setDescription(Description);
        this.setLocation(Location);
        this.setWebsite(Website);
        this.setMaplink(Maplink);
    }

    PlaceFirebase(String Name, String Location, String Website, String Maplink){

        this.setName(Name);
        this.setLocation(Location);
        this.setWebsite(Website);
        this.setMaplink(Maplink);
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMaplink() {
        return Maplink;
    }

    public void setMaplink(String maplink) {
        Maplink = maplink;
    }
}
