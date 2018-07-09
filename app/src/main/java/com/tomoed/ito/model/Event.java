package com.tomoed.ito.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Event {
    private String name;
    private String description;
    private String category;
    private double time;
    private LatLng meetupLocation;
    private LatLng destinationLocation;
    private String createdBy;
    private List<User> joined;

    public Event() {

    }

    public Event(String name, String description, String category, double time, LatLng meetupLocation, LatLng destinationLocation, String createdBy, List<User> joined) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.time = time;
        this.meetupLocation = meetupLocation;
        this.destinationLocation = destinationLocation;
        this.createdBy = createdBy;
        this.joined = joined;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public LatLng getMeetupLocation() {
        return meetupLocation;
    }

    public void setMeetupLocation(LatLng meetupLocation) {
        this.meetupLocation = meetupLocation;
    }

    public LatLng getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(LatLng destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<User> getJoined() {
        return joined;
    }

    public void setJoined(List<User> joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return "Event {" +
                "name='" + name + "\', " +
                "description='" + description + "\', " +
                "category='" + category + "\', " +
                "time='" + time + "\', " +
                "meetupLocation=" + meetupLocation + "\', " +
                "destinationLocation=" + destinationLocation + "\', " +
                "createdBy=" + createdBy + "\', " +
                "joined=" + Arrays.toString(joined == null ? joined.toArray() : new User[0]) + "\', " +
                '}';
    }

}
