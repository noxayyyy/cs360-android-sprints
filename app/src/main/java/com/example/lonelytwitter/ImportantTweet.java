package com.example.lonelytwitter;

import java.util.Date;

public class ImportantTweet extends Tweet {
    ImportantTweet(String msg) {
        super(msg);
    }

    ImportantTweet(String msg, Date d) {
        super(msg, d);
    }

    public @Override Boolean is_important() {
        return true;
    }
}
