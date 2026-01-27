package com.example.lonelytwitter;

import java.util.Date;

public class AngryMood {
    String mood;
    Date date;

    AngryMood() {
        mood = "angry";
        date = new Date();
    }

    AngryMood(Date d) {
        mood = "angry";
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
