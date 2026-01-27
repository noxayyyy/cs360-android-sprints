package com.example.lonelytwitter;

import java.util.Date;

public abstract class Tweet implements Tweetable {
    private Date date;
    private String message;

    Tweet(String msg) {
        date = new Date();
        message = msg;
    }

    Tweet(String msg, Date d) {
        date = d;
        message = msg;
    }

    public abstract Boolean is_important();

    public Date get_date() {
        return date;
    }

    public String get_msg() {
        return message;
    }

    public void set_date(Date d) {
        date = d;
    }

    public void set_msg(String msg) {
        message = msg;
    }
}
