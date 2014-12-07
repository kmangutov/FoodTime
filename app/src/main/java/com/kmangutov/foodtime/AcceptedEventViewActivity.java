package com.kmangutov.foodtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Chihiro on 11/4/2014.
 */

public class AcceptedEventViewActivity extends Activity {
    Format formatter = new SimpleDateFormat("EEE hh:mm aaa");
    ArrayList<User> friendAcceptedList = new ArrayList<User>();
    ArrayList<User> friendNoList = new ArrayList<User>();
    ArrayList<String> friendAcceptedListStr = new ArrayList<String>();
    ArrayList<String> friendNoListStr = new ArrayList<String>();
    //String[] name_accepted = {"Jon", "Kirill", "Harrison"};
    //String[] name_no = {"Borg","Isra"};
    public final static String EVENTNAME_EXTRA="com.kmangutov.foodtime._EN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accepted_event_view_activity);
        setTitle("Review Meetup");

        Intent intent = getIntent();
        int event_index = intent.getIntExtra("eventIndex",0);

        //name_accepted = new String[]{"Jon", "Kirill", "Harrison"};
        //name_no = new String[]{"Borg","Isra"};
        GlobalClass vars = (GlobalClass) getApplicationContext();
        Event event = vars.getEventList().get(event_index);

        friendAcceptedList = event.getAcceptedFriends();
        friendNoList = event.getWaitingFriends();

        //Need the String version of invited friend names, so take them out...
        for (int i=0; i<friendAcceptedList.size(); ++i) {
            friendAcceptedListStr.add(friendAcceptedList.get(i).getName().toString());
        }
        for (int i=0; i<friendNoList.size(); ++i) {
            friendNoListStr.add(friendNoList.get(i).getName().toString());
        }

        ListView myListView1 = (ListView)findViewById(R.id.listViewYes);
        final ArrayAdapter adapter1 = new ArrayAdapter<String>(this,
                R.layout.list_text_white,  R.id.list_item, friendAcceptedListStr);
        myListView1.setAdapter(adapter1);
        //friendAcceptedList.addAll(Arrays.asList(name_accepted));

        ListView myListView2 = (ListView)findViewById(R.id.listViewNo);
        final ArrayAdapter adapter2 = new ArrayAdapter<String>(this,
               R.layout.list_text_white,  R.id.list_item, friendNoListStr);
        myListView2.setAdapter(adapter2);
        //friendNoList.addAll(Arrays.asList(name_no));


        ((TextView) findViewById(R.id.textEventName)).setText(event.getTitle());
        ((TextView) findViewById(R.id.textTime)).setText(formatter.format(event.getStartTime()));
        ( (TextView) findViewById(R.id.textLocation)).setText(event.getLocation());//+ ", 204 E Green St, 61820");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accepted_event_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void edit_button(View v){

        startActivity(new Intent(this, ScheduleEventActivity.class));
    }

    public void leave_button(View v){
        //delete current event record
        finish();
    }
}
