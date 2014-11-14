package com.kmangutov.foodtime;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.kmangutov.foodtime.TimeBar.TimeBar;


public class DummyEventActivity extends Activity {

    String[] friends = {"Isra", "Borg", "Kirill"};
    private ListView friendsList;
    private ArrayAdapter friendArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_event);
        setTitle("Accept This Invitation?");

        friendsList = (ListView)findViewById(R.id.friendListView);
        friendArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);
        friendsList.setAdapter(friendArrayAdapter);
        TimeBar tb = (TimeBar) findViewById(R.id.eventTimeBar);
        tb.addDummyTime(12, 15, Color.GREEN);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dummy_event, menu);
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

    public void acceptEvent(View v) {
        finish();
    }
}
