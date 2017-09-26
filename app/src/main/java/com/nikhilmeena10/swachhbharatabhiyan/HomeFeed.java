package com.nikhilmeena10.swachhbharatabhiyan;

import android.widget.Button;

import java.io.Serializable;

/**
 * Created by Nikhil_Meena on 26-09-2017.
 */

public class HomeFeed implements Serializable {
    int imageID, profile_imageID;
    String name, date, time, desc, number_of_likes;
    Button like;

    public void setData(int profile_imageID, String name, String date, String time,
                        String desc, int imageID, String number_of_likes) {
        this.profile_imageID = profile_imageID;
        this.name = name;
        this.date = date;
        this.time = time;
        this.desc = desc;
        this.imageID = imageID;
        this.number_of_likes = number_of_likes;
    }
}