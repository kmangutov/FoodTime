package com.kmangutov.foodtime;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jonathan on 11/4/2014.
 */
public class FriendAddActivity extends Activity {

    FriendListAdapter adapter = null;
    ArrayList<User> recList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_add_activity);

        //pregenerated
        recList.add(new User("smthn4","Jon", "Urbana, IL"));
        recList.add(new User("smthn5","Borg", "Urbana, IL"));
        recList.add(new User("smthn6","Harrison", "Champaign, IL"));

        showListView();
        addButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void showListView() {

        //ArrayAdapter for friendList array
        ListView myListView = (ListView) findViewById(R.id.recommendListView);
        adapter = new FriendListAdapter(this,
                R.layout.friend_checkbox_layout, recList);
        ListView listView = (ListView) findViewById(R.id.recommendListView);

        listView.setAdapter(adapter);

        //What do we do when an item in list is clicked?
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do nothing
            }
        });
    }

    private void addButton() {

        //TODO: Prompt the User


        Button myButton = (Button) findViewById(R.id.but_addlist);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Added the following:\n");

                ArrayList<User> friendList = adapter.friendList;

                for(int i=0;i<friendList.size();i++){
                    User user = friendList.get(i);
                    if(user.isSelected()){
                        responseText.append("\n" + user.getName());
                        //TODO: instead, add to global list of friends
                        friendList.remove(user);
                        i--;
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

                finish();

            }
        });



    }


}
