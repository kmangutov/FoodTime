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
    ArrayList<User> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_add_activity);

        GlobalClass vars = (GlobalClass) getApplicationContext();
        ArrayList<User> userList = vars.getUserList();
        friendList = vars.getFriendList();
        for(int i=0; i<userList.size(); ++i) {
            if (!friendList.contains(userList.get(i))) {
                recList.add(userList.get(i));
            }
        }

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

                GlobalClass vars = (GlobalClass) getApplicationContext();
                ArrayList<User> recList = adapter.userList;

                for(int i=0;i<recList.size();i++){
                    User user = recList.get(i);
                    if(user.isSelected()){
                        responseText.append("\n" + user.getName());
                        vars.getFriendList().add(user);
                    }
                }

                ((GlobalClass)getApplicationContext()).unsetAll();

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

                finish();

            }
        });



    }


}
