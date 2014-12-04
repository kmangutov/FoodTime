package com.kmangutov.foodtime;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Vector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kmangutov.foodtime.TimeBar.TimeBar;
import com.kmangutov.foodtime.TimeBar.TimeSlot;

import org.joda.time.LocalTime;

public class ScheduleEventActivity extends Activity implements TabListener{

    Fragment f = null;
    InviteTabFragment tf = null;

    //listeners for inputMeetingTitle and inputLocation
    //reacts to "Done" which corresponds to but_schedule
    Button mButton;
    EditText mEdit0;
    EditText mEdit1;
    TimeBar mTimeBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle("Schedule a Meetup");
//        setContentView(R.layout.activity_schedule_invite_friend);

        Tab inviteTab = actionBar.newTab();
        inviteTab.setText("Invite Friends");
        inviteTab.setTabListener(this);

        Tab timeLocationTab = actionBar.newTab();
        timeLocationTab.setText("Pick Time and Location");
        timeLocationTab.setTabListener(this);

        actionBar.addTab(inviteTab);
        actionBar.addTab(timeLocationTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule_invite_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {

        int tabIndex = tab.getPosition();

        if (tabIndex == 0){
            InviteTabFragment fragmentFriend = new InviteTabFragment();
            f = fragmentFriend;
        }
        else {
            TimeLocationTabFragment fragmentTimeLocation = new TimeLocationTabFragment();
            f = fragmentTimeLocation;
        }

        System.out.println("Tab " + tab.getPosition());

        fragmentTransaction.replace(android.R.id.content, f);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    //called when "Done" is pressed with id but_schedule
    public void createEventButton(View v){

        //source: http://stackoverflow.com/questions/4531396/get-value-of-a-edit-text-field
        mButton = (Button)findViewById(R.id.but_schedule);
        mEdit0   = (EditText)findViewById(R.id.inputMeetingTitle);
        mEdit1 = (EditText)findViewById(R.id.inputLocation);

        mTimeBar = (TimeBar)findViewById(R.id.timeBar);
        TimeSlot slot;
        int start_hour = 0;
        int start_minute = 0;
        int end_hour = 0;
        int end_minute = 0;
        if(mTimeBar.getTimeSlot()!=null)
        {
            slot = mTimeBar.getTimeSlot();
            LocalTime start_time = slot.getBeginTime();
            LocalTime end_time = slot.getEndTime();
            if(start_time!=null)
            {
                start_hour = start_time.getHourOfDay();
                start_minute = start_time.getMinuteOfHour();
            }
            else
            {
                System.out.println("start_time is null");
            }

            if(end_time!=null)
            {
                end_hour = end_time.getHourOfDay();
                end_minute = end_time.getMinuteOfHour();
            }
            else
            {
                System.out.println("end_time is null");
            }

            System.out.println("inputMeetingTitle: " + mEdit0.getText().toString());
            System.out.println("inputLocation: " + mEdit1.getText().toString());
            System.out.println("Start Time: " + Integer.toString(start_hour) + ":" + Integer.toString(start_minute));
            System.out.println("End Time: " + Integer.toString(end_hour) + ":" + Integer.toString(end_minute));
        }
        else
        {
            Toast.makeText(this, "Invalid timeslot", Toast.LENGTH_LONG);
            return;
        }





        finish();
    }

}
