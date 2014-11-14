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


public class ScheduleEventActivity extends Activity implements TabListener{

    Fragment f = null;
    InviteTabFragment tf = null;

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


}
