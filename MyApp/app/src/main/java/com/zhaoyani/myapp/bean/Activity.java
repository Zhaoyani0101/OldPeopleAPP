package com.zhaoyani.myapp.bean;

public class Activity {
    private int _id;
    private int activity_img;
    private String activity_title;
    private String activity_startTime;
    private String activity_endTime;
    private String activity_creator;


    public Activity(int _id, int activity_img, String activity_title, String activity_startTime, String activity_endTime, String activity_creator) {
        this._id = _id;
        this.activity_title = activity_title;
        this.activity_startTime = activity_startTime;
        this.activity_endTime = activity_endTime;
        this.activity_creator = activity_creator;
        this.activity_img = activity_img;
    }

    public Activity() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getActivity_title() {
        return activity_title;
    }

    public void setActivity_title(String activity_title) {
        this.activity_title = activity_title;
    }

    public String getActivity_startTime() {
        return activity_startTime;
    }

    public void setActivity_startTime(String activity_startTime) {
        this.activity_startTime = activity_startTime;
    }

    public String getActivity_endTime() {
        return activity_endTime;
    }

    public void setActivity_endTime(String activity_endTime) {
        this.activity_endTime = activity_endTime;
    }

    public String getActivity_creator() {
        return activity_creator;
    }

    public void setActivity_creator(String activity_creator) {
        this.activity_creator = activity_creator;
    }
    public int getActivity_img() {
        return activity_img;
    }

    public void setActivity_id(int activity_img) {
        this.activity_img = activity_img;
    }


}
