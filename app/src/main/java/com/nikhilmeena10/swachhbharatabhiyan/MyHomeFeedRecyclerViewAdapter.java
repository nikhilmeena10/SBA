package com.nikhilmeena10.swachhbharatabhiyan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikhilmeena10.swachhbharatabhiyan.HomeFeedFragment.OnListFragmentInteractionListener;
import com.nikhilmeena10.swachhbharatabhiyan.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHomeFeedRecyclerViewAdapter extends RecyclerView.Adapter<HomeFeedViewHolder> {

    private final List<HomeFeed> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyHomeFeedRecyclerViewAdapter(List<HomeFeed> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public HomeFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_homefeed, parent, false);
        return new HomeFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeFeedViewHolder holder, int position) {
        HomeFeed homeFeed = mValues.get(position);
        holder.profile_image.setImageResource(homeFeed.profile_imageID);
        holder.name.setText(homeFeed.name);
        holder.date.setText(homeFeed.date);
        holder.time.setText(homeFeed.time);
        holder.desc.setText(homeFeed.desc);
        holder.post_image.setImageResource(homeFeed.imageID);
        holder.number_of_likes.setText(homeFeed.number_of_likes);
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);

        /*holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /*public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        //public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.id);
            //mContentView = (TextView) view.findViewById(R.id.content);
        }

        //@Override
        //public String toString() {
          //  return super.toString() + " '" + mContentView.getText() + "'";
        //}
    }*/
}
