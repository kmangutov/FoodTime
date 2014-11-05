package com.kmangutov.foodtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/*
    Friend list stuff. http://www.androidbegin.com/tutorial/android-delete-multiple-selected-items-listview-tutorial/
 */

public class FriendActivity extends Activity {
    // Declare Variables
    ListView list;
    //ListViewAdapter listviewadapter;
    //List<Users> worldpopulationlist = new ArrayList<Uers>();
    final ArrayList<String> friendList = new ArrayList<String>();
    String[] name;
    String[] username;
    String[] location;
    int[] picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity);


        // Generate sample data into string arrays
        name = new String[] { "Kirill", "Isra", "Xi" };

        username = new String[] { "smthn", "smthn2","smthn3" };

        location = new String[] { "Urbana, IL", "FLORIDAAAAAAAAA", "Champaign, IL" };

        //picture = new int[] { R.drawable., R.drawable., R.drawable. };



        ListView myListView = (ListView)findViewById(R.id.friendListView);
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, friendList);
        myListView.setAdapter(adapter);

        //What do we do when an item in list is clicked?
        myListView.setOnItemClickListener(onListClick);

        friendList.addAll(Arrays.asList(name));

        /*
        for (int i = 0; i < rank.length; i++) {
            WorldPopulation worldpopulation = new WorldPopulation(flag[i],
                    rank[i], country[i], population[i]);
            worldpopulationlist.add(worldpopulation);*/
        }

        /*
        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        // Pass results to ListViewAdapter Class
        listviewadapter = new ListViewAdapter(this, R.layout.listview_item,
                worldpopulationlist);

        // Binds the Adapter to the ListView
        list.setAdapter(listviewadapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click
        list.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listviewadapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listviewadapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                WorldPopulation selecteditem = listviewadapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                listviewadapter.remove(selecteditem);
                            }
                        }
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.activity_main, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listviewadapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
        });

    }
    */

    private AdapterView.OnItemClickListener onListClick=new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //selected_name = ((TextView)view).getText().toString();
        }
    };


    public void add_button(View v){
        startActivity(new Intent(FriendActivity.this, FriendAddActivity.class));
    }

    //TODO:
    public void remove_button(View v){
        //Prompt the User
    }

}
