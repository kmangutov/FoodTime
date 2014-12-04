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


public class HomeActivity extends Activity {

    //ArrayList<String> invitedArray;
    String[] invitedArray= { "Papa Johns - Friday", "Quiznos - Saturday" };
    //ArrayList<String> acceptedArray;
    String[] acceptedArray = { "Sakari Sushi - Sunday", "Fro Yo - Sunday" };
    private ListView acceptedList, invitedList;
    private ArrayAdapter invitedArrayAdapter, acceptedArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        GlobalClass vars = (GlobalClass) getApplicationContext();
        User me = vars.getUser();
        ArrayList<Event> eventList = vars.getEventList();
        for (int i=0; i<eventList.size(); ++i) {
            if (eventList.get(i).getWaitingFriends().contains(me))
                invitedArray.add(eventList.get(i).getTitle()+" - "+eventList.get(i).getStartTime().toString());
            else if (eventList.get(i).getAcceptedFriends().contains(me))
                acceptedArray.add(eventList.get(i).getTitle()+" - "+eventList.get(i).getStartTime().toString());
        }*/

        invitedList = (ListView) findViewById(R.id.invited_list);
        invitedArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, invitedArray);
        invitedList.setAdapter(invitedArrayAdapter);
        invitedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), DummyEventActivity.class);
                startActivity(intent);
            }
        });

        acceptedList = (ListView) findViewById(R.id.accepted_list);
        acceptedArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, acceptedArray);
        acceptedList.setAdapter(acceptedArrayAdapter);
        acceptedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AcceptedEventViewActivity.class);
                intent.putExtra("passed_eventname", acceptedList.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

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

        startActivity(new Intent(HomeActivity.this, ScheduleEventActivity.class));
    }

}
