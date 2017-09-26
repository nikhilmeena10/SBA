package com.nikhilmeena10.swachhbharatabhiyan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nikhil_Meena on 26-09-2017.
 */

public class HomeFeedViewHolder extends RecyclerView.ViewHolder {
    TextView name, date, time, desc, number_of_likes;
    ImageView post_image, profile_image;
    ImageButton like;

    public HomeFeedViewHolder(View view) {
        super(view);

        this.post_image = (ImageView) view.findViewById(R.id.post_image);
        this.profile_image = (ImageView) view.findViewById(R.id.post_profile_photo);

        this.name = (TextView) view.findViewById(R.id.post_profile_name);
        this.date = (TextView) view.findViewById(R.id.post_date);
        this.time = (TextView) view.findViewById(R.id.post_time);
        this.desc = (TextView) view.findViewById(R.id.post_content);
        this.number_of_likes = (TextView) view.findViewById(R.id.post_likes);

        this.like = (ImageButton) view.findViewById(R.id.post_like_button);
    }
}
