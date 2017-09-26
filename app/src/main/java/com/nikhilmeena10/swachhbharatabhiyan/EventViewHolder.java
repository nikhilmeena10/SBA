package com.nikhilmeena10.swachhbharatabhiyan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nikhil_Meena on 26-09-2017.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {
    TextView date, time, location, organizer, desc, funds_needed, number_of_people_going, current_funds;
    ImageView image;
    Button going, donate;

    public EventViewHolder(View view) {
        super(view);
        this.image = (ImageView) view.findViewById(R.id.event_location_photo);

        this.date = (TextView) view.findViewById(R.id.event_date);
        this.time = (TextView) view.findViewById(R.id.event_timing);
        this.location = (TextView) view.findViewById(R.id.event_location_name);
        this.organizer = (TextView) view.findViewById(R.id.event_organiser);
        this.desc = (TextView) view.findViewById(R.id.event_desc);
        this.funds_needed = (TextView) view.findViewById(R.id.funds_needed);
        this.number_of_people_going = (TextView) view.findViewById(R.id.number_of_people_going);
        this.current_funds = (TextView) view.findViewById(R.id.current_funds);

        this.going = (Button) view.findViewById(R.id.event_going_button);
        this.donate = (Button) view.findViewById(R.id.event_donation_button);
    }

}
