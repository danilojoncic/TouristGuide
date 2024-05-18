package com.example.touristguide.domain;

public class Activity {
    private int activity_id;
    private String tag;

    public Activity(int activity_id, String tag) {
        this.activity_id = activity_id;
        this.tag = tag;
    }

    public Activity() {
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
