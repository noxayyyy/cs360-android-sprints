package com.example.lonelytwitter;

import java.util.Date;

public class HappyMood implements Mood {
    String mood;
    Date date;

    HappyMood() {
        mood = "happy";
        date = new Date();
    }

    HappyMood(Date d) {
        mood = "happy";
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
