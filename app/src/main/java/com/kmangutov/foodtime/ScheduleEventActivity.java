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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.kmangutov.foodtime.TimeBar.TimeBar;
import com.kmangutov.foodtime.TimeBar.TimeBarUpdateListener;
import com.kmangutov.foodtime.TimeBar.TimeSlot;
import org.joda.time.LocalTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    boolean startAM;
    boolean endAM;

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

        if (start_hour>=12) startAM=false;
        else startAM=true;

        if (end_hour>=12) endAM=false;
        else endAM=true;

        start_hour = (start_hour == 12)?12:(start_hour % 12);
        end_hour = (end_hour == 12)?12:(end_hour % 12);

        String start_minute_str = Integer.toString(start_minute);
        start_minute_str = String.format("%02d", Integer.parseInt(start_minute_str));
        String end_minute_str = Integer.toString(end_minute);
        end_minute_str = String.format("%02d", Integer.parseInt(end_minute_str));

        mStartTime.setText("Start Time: " + start_hour + ":" + start_minute_str);
        mEndTime.setText("End Time: " + end_hour + ":" + end_minute_str);
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

        //CURRENT DATE???
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy.MMMM.dd").format(date);
        String startStDate = modifiedDate+" "+String.format("%2d",start_hour)+":"+String.format("%2d",start_minute)+" "+(startAM?"AM":"PM");
        String endStDate = modifiedDate+" "+String.format("%2d",start_hour)+":"+String.format("%2d",start_minute)+" "+(endAM?"AM":"PM");
        Date startDate = null;
        Date endDate=null;
        try {
            startDate = new SimpleDateFormat("yyyy.MMMM.dd hh:mm aaa", Locale.ENGLISH).parse(startStDate);
            endDate = new SimpleDateFormat("yyyy.MMMM.dd hh:mm aaa", Locale.ENGLISH).parse(endStDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Finally create the event Obj and add it to the EVENTS DB
        GlobalClass vars = (GlobalClass) getApplicationContext();

        Event event = new Event(mEdit0.getText().toString(),mEdit1.getText().toString(),startDate, endDate);
        //We have alraedy invited and accepted ourselves cuz... well y'know
        event.addAcceptedFriends(vars.getUser());
        //TODO: GET THE ARRAYLIST OF SELECTED FRIENDS FROM LIST, AND JUST SLAP IT ON TO SETWAITINGFRIENDS, and that shud be it
        //event.setWaitingFriends();
        //TEMPORARY CODE INVITING EVERYONE U KNOW:
        event.setWaitingFriends(vars.getUser().getFriendList());

        //ADD NEW EVENT TO EVENTS DB
        vars.getEventList().add(event);

        //Unticks everyone, because the tick can carry over to friendlist activity, etc. wow shit code
        ((GlobalClass)getApplicationContext()).unsetAll();

        finish();
    }

}
