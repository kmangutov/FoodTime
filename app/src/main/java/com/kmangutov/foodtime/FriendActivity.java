package com.kmangutov.foodtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/*
    Friend list stuff. http://www.androidbegin.com/tutorial/android-delete-multiple-selected-items-listview-tutorial/
 */

public class FriendActivity extends Activity {
    // Declare Variables
    ListView list;
    MyCustomAdapter adapter = null;
    ArrayList<User> friendList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity);

        //pregenerated
        friendList.add(new User("smthn","Kirill", "Urbana, IL"));
        friendList.add(new User("smthn2","Isra", "FLORIDAAAAAA"));
        friendList.add(new User("smthn3","Xi", "Champaign, IL"));

        showListView();
        removeButton();
        /*
        for (int i = 0; i < rank.length; i++) {
            WorldPopulation worldpopulation = new WorldPopulation(flag[i],
                    rank[i], country[i], population[i]);
            worldpopulationlist.add(worldpopulation);*/

    }

    private void showListView() {

        //ArrayAdapter for friendList array
        ListView myListView = (ListView) findViewById(R.id.friendListView);
        adapter = new MyCustomAdapter(this,
                R.layout.friend_checkbox_layout, friendList);
        ListView listView = (ListView) findViewById(R.id.friendListView);

        listView.setAdapter(adapter);

        //What do we do when an item in list is clicked?
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Open the User's info page
                //selected_name = ((TextView)view).getText().toString();
                //Unimplemented
            }
        });
    }

    /*
    Go to the friend-search&adding page
     */
    public void add_button(View v){
        startActivity(new Intent(FriendActivity.this, FriendAddActivity.class));
    }

    private class MyCustomAdapter extends ArrayAdapter<User> {

        public ArrayList<User> friendList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<User> friendList) {
            super(context, textViewResourceId, friendList);
            this.friendList = new ArrayList<User>();
            this.friendList.addAll(friendList);
        }

        private class ViewHolder {
            TextView location;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.friend_checkbox_layout, null);

                holder = new ViewHolder();
                holder.location = (TextView) convertView.findViewById(R.id.loc);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        User user = (User) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        user.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            User user = friendList.get(position);
            holder.location.setText(" (" +  user.getLocation() + ")");
            holder.name.setText(user.getName());
            holder.name.setChecked(user.isSelected());
            holder.name.setTag(user);

            return convertView;

        }

    }

    private void removeButton() {

        //TODO: Prompt the User


        Button myButton = (Button) findViewById(R.id.but_delFriendList);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("De-friending the following:\n");

                ArrayList<User> friendList = adapter.friendList;

                for(int i=0;i<friendList.size();i++){
                    User user = friendList.get(i);
                    if(user.isSelected()){
                        responseText.append("\n" + user.getName());
                        friendList.remove(user);
                        i--;
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

                adapter.notifyDataSetChanged();

            }
        });

    }

}
