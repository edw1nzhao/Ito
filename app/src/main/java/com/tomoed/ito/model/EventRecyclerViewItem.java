package com.tomoed.ito.model;

public class EventRecyclerViewItem {

    private String eventName;
    private int eventImageId;

    public EventRecyclerViewItem(String eventName, int eventImageId) {
        this.eventName = eventName;
        this.eventImageId = eventImageId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventImageId() {
        return eventImageId;
    }

    public void setEventImageId(int eventImageId) {
        this.eventImageId = eventImageId;
    }
}
