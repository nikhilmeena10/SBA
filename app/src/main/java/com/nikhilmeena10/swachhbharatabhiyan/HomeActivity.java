package com.nikhilmeena10.swachhbharatabhiyan;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    //private BottomNavigationView mBottomNavigationView;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Fragment mFragment = null;

    private String TAG = HomeActivity.class.getSimpleName();
    String EVENT_TAG = "EVENTTAG";

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String events_url = "http://localhost:8000/events_data.json";
    private static String home_feed_url = "http://localhost:8000/home_feed_data.json";

    ArrayList<Event> events_data_list;
    ArrayList<HomeFeed> homeFeeds_data_list;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.nav_home);
                    mFragment = HomeFeedFragment.newInstance(1);
                    return true;
                case R.id.navigation_events:
                    //mTextMessage.setText(R.string.nav_events);
                    mFragment = EventFragment.newInstance(1);
                    return true;
                case R.id.navigation_map:
                    mTextMessage.setText(R.string.nav_map);
                    return true;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment, "TAG").commit();
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        String[] drawer_menu = {"Profile","Friends","Achievements"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, drawer_menu));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        events_data_list = new ArrayList<>();
        homeFeeds_data_list = new ArrayList<>();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            // Highlight the selected item, update the title, and close the drawer
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle("......");

            String text= "menu click... should be implemented";
            Toast.makeText(HomeActivity.this, text, Toast.LENGTH_LONG).show();
            mDrawerLayout.closeDrawer(mDrawerList);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class FetchJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to events_url and getting response
            String events_jsonStr = sh.makeServiceCall(events_url);
            String home_feed_jsonStr = sh.makeServiceCall(home_feed_url);

            Log.e(TAG, "Response from events_url: " + events_jsonStr);
            Log.e(TAG, "Response from home_feed_url: " + home_feed_jsonStr);

            if (events_jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(events_jsonStr);

                    // Getting JSON Array node
                    JSONArray events = jsonObj.getJSONArray("event");

                    // looping through All Contacts
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        String imageURL = c.getString("image");
                        String date = c.getString("date");
                        String time = c.getString("time");
                        String location = c.getString("location");
                        String organiser = c.getString("organiser");
                        String desc = c.getString("desc");
                        String going = c.getString("going");
                        String current_funds = c.getString("donations");
                        String funds_needed = c.getString("funds_needed");

                        Event event = new Event();
                        event.setData(date, time, location, organiser, desc, going, funds_needed, current_funds,
                                Integer.parseInt(imageURL));

                        events_data_list.add(event);
                        /*
                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contact);*/
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            if (home_feed_jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(home_feed_jsonStr);

                    // Getting JSON Array node
                    JSONArray feed = jsonObj.getJSONArray("feed");

                    // looping through All Contacts
                    for (int i = 0; i < feed.length(); i++) {
                        JSONObject c = feed.getJSONObject(i);

                        String profile_image_url = c.getString("profile_image");
                        String name = c.getString("name");
                        String date = c.getString("date");
                        String time = c.getString("time");
                        String desc = c.getString("desc");
                        String post_imageURL = c.getString("post_image");
                        String likes = c.getString("likes");

                        HomeFeed homeFeed = new HomeFeed();
                        homeFeed.setData(Integer.parseInt(profile_image_url), name, date, time,
                                desc, Integer.parseInt(post_imageURL), likes);

                        homeFeeds_data_list.add(homeFeed);
                        /*
                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contact);*/
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            Bundle bundle = new Bundle();
            bundle.putSerializable("EVENTTAG", events_data_list);
            bundle.putSerializable("HOMEFEEDTAG", homeFeeds_data_list);
            //EventFragment fragment = new EventFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment, "TAG").commit();
            /**
             * Updating parsed JSON data into ListView
             * */
            /*ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            lv.setAdapter(adapter);*/
        }

    }
}
