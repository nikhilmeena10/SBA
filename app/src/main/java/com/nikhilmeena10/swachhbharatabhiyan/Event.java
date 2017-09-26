package com.nikhilmeena10.swachhbharatabhiyan;

import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Nikhil_Meena on 25-09-2017.
 */

public class Event implements Serializable {
    String date, time, location, organizer, desc, funds_needed, number_of_people_going, current_funds;
    int imageID;
    Button going, donate;

    public void setData(String date, String time, String location,
                        String organizer, String desc, String number_of_people_going,
                        String funds_needed, String current_funds, int imageID) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
        this.desc = desc;
        this.number_of_people_going = number_of_people_going;
        this.funds_needed = funds_needed;
        this.imageID = imageID;
        this.current_funds = current_funds;
    }
}