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
import android.widget.TextView;
import android.widget.Toast;

import com.kmangutov.foodtime.TimeBar.TimeBar;
import com.kmangutov.foodtime.TimeBar.TimeBarUpdateListener;
import com.kmangutov.foodtime.TimeBar.TimeSlot;

import org.joda.time.LocalTime;

public class ScheduleEventActivity extends Activity implements TabListener, TimeBarUpdateListener {

    Fragment f = null;
    InviteTabFragment tf = null;

    //listeners for inputMeetingTitle and inputLocation
    //reacts to "Done" which corresponds to but_schedule
    Button mButton;
    EditText mEdit0;
    EditText mEdit1;
    TimeBar mTimeBar;
    TextView mStartTime;
    TextView mEndTime;

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

        try {
            mTimeBar = (TimeBar) findViewById(R.id.timeBar);
            mTimeBar.listener = this;
        }catch(Exception e){}
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    //update start time and end time TextView elements in fragment_time.xml
    public void timeBarUpdate() {



        mStartTime = (TextView)findViewById(R.id.start_time);
        mEndTime = (TextView)findViewById(R.id.end_time);
        mTimeBar = (TimeBar)findViewById(R.id.timeBar);
        TimeSlot slot = mTimeBar.getTimeSlot();

        if(slot == null) {

            mStartTime.setText("Start Time");
            mEndTime.setText("End Time");
            return;
        }


        LocalTime start_time = slot.getBeginTime();
        LocalTime end_time = slot.getEndTime();

        int start_hour = start_time.getHourOfDay();
        int start_minute = start_time.getMinuteOfHour();
        int end_hour = end_time.getHourOfDay();
        int end_minute = end_time.getMinuteOfHour();

        mStartTime.setText("Start Time: " + start_hour + ":" + start_minute);
        mEndTime.setText("End Time: " + end_hour + ":" + end_minute);
    }

    //called when "Done" is pressed with id but_schedule
    public void createEventButton(View v){

        //source: http://stackoverflow.com/questions/4531396/get-value-of-a-edit-text-field
        mButton = (Button)findViewById(R.id.but_schedule);
        mEdit0   = (EditText)findViewById(R.id.inputMeetingTitle);
        mEdit1 = (EditText)findViewById(R.id.inputLocation);


        mTimeBar = (TimeBar)findViewById(R.id.timeBar);
        TimeSlot slot = mTimeBar.getTimeSlot();

        if(slot == null) {

            Toast.makeText(this, "Invalid timeslot", Toast.LENGTH_LONG).show();
            return;
        }


        LocalTime start_time = slot.getBeginTime();
        LocalTime end_time = slot.getEndTime();

        int start_hour = start_time.getHourOfDay();
        int start_minute = start_time.getMinuteOfHour();
        int end_hour = end_time.getHourOfDay();
        int end_minute = end_time.getMinuteOfHour();

        mStartTime.setText("Start Time: " + start_hour + ":" + start_minute);
        mEndTime.setText("End Time: " + end_hour + ":" + end_minute);

        System.out.println("inputMeetingTitle: " + mEdit0.getText());
        System.out.println("inputLocation: " + mEdit1.getText());
        System.out.println("Start Time: " + start_hour + ":" + start_minute);
        System.out.println("End Time: " + end_hour + ":" + end_minute);

        finish();
    }

}
