package com.example.lonelytwitter;

import java.util.Date;

public class NormalTweet extends Tweet {
    NormalTweet(String msg) {
        super(msg);
    }

    NormalTweet(String msg, Date d) {
        super(msg, d);
    }

    public @Override Boolean is_important() {
        return false;
    }
}
