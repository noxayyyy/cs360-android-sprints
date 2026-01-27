package com.example.lonelytwitter;

import java.util.Date;

public class SadMood {
    String mood;
    Date date;

    SadMood() {
        mood = "sad";
        date = new Date();
    }

    SadMood(Date d) {
        mood = "sad";
        date = d;
    }

    public String get_mood() {
        return mood;
    }

    public Date get_date() {
        return date;
    }

    public void set_date(Date d) {
        date = d;
    }
}
