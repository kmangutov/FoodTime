package com.kmangutov.foodtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Chihiro on 11/4/2014.
 */

public class AcceptedEventViewActivity extends Activity {

    ArrayList<String> friendAcceptedList = new ArrayList<String>();
    ArrayList<String> friendNoList = new ArrayList<String>();
    String[] name_accepted = {"Jon", "Kirill", "Harrison"};
    String[] name_no = {"Borg","Isra"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accepted_event_view_activity);

        name_accepted = new String[]{"Jon", "Kirill", "Harrison"};
        name_no = new String[]{"Borg","Isra"};

        ListView myListView1 = (ListView)findViewById(R.id.listViewYes);
        final ArrayAdapter adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, friendAcceptedList);
        myListView1.setAdapter(adapter1);
        friendAcceptedList.addAll(Arrays.asList(name_accepted));

        ListView myListView2 = (ListView)findViewById(R.id.listViewNo);
        final ArrayAdapter adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, friendNoList);
        myListView2.setAdapter(adapter2);
        friendNoList.addAll(Arrays.asList(name_no));
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

    public void edit_button(){
        startActivity(new Intent(this, SchedulingActivity.class));
    }

    public void leave_button(){
        //delete current event record
    }
}
