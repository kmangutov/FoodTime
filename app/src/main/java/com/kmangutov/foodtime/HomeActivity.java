package com.kmangutov.foodtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class HomeActivity extends Activity {

    ArrayList<String> invitedArray = new ArrayList<String>();
    //String[] invitedArray= { "Papa Johns - Friday", "Quiznos - Saturday" };
    ArrayList<String> acceptedArray = new ArrayList<String>();
    //String[] acceptedArray = { "Sakari Sushi - Sunday", "Fro Yo - Sunday" };
    private ListView acceptedList, invitedList;
    private ArrayAdapter invitedArrayAdapter, acceptedArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        populateEvents();


    }

    private void populateEvents() {
        GlobalClass vars = (GlobalClass) getApplicationContext();
        User me = vars.getUser();
        ArrayList<Event> eventList = vars.getEventList();

        invitedArray.clear();
        acceptedArray.clear();

        Format formatter = new SimpleDateFormat("EEE hh:mm aaa");

        for (int i=0; i<eventList.size(); ++i) {
            if (eventList.get(i).getWaitingFriends().contains(me))
                invitedArray.add(eventList.get(i).getTitle()+" - "+formatter.format(eventList.get(i).getStartTime()));
            else if (eventList.get(i).getAcceptedFriends().contains(me))
                acceptedArray.add(eventList.get(i).getTitle()+" - "+formatter.format(eventList.get(i).getStartTime()));
        }

        invitedList = (ListView) findViewById(R.id.invited_list);
        invitedArrayAdapter = new ArrayAdapter(this, R.layout.list_text_white,  R.id.list_item, invitedArray);
        invitedList.setAdapter(invitedArrayAdapter);
        invitedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), DummyEventActivity.class);
                intent.putExtra("eventIndex", i);
                startActivity(intent);
            }
        });

        acceptedList = (ListView) findViewById(R.id.accepted_list);
        acceptedArrayAdapter = new ArrayAdapter(this,  R.layout.list_text_white,  R.id.list_item, acceptedArray);
        acceptedList.setAdapter(acceptedArrayAdapter);

        acceptedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AcceptedEventViewActivity.class);
                //intent.putExtra("passed_eventname", acceptedList.getItemAtPosition(i).toString());
                intent.putExtra("eventIndex", i);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //Update the darn lists when you return to the main home screen from somewhere (especially via the RETURN/BACK button)
        populateEvents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_person:
                startActivity(new Intent(HomeActivity.this, FriendActivity.class));
                return true;
            // Can add other actions here
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void scheduleMeeting(View v){
        GlobalClass vars = (GlobalClass) getApplicationContext();
        User me = vars.getUser();
        if(me.friendList.isEmpty()){
            startActivity(new Intent(HomeActivity.this, FriendActivity.class));
        }
        else {
            startActivity(new Intent(HomeActivity.this, ScheduleEventActivity.class));
        }
    }

}
