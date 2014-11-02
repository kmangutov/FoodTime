package com.kmangutov.foodtime;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/*
    Friend list stuff. http://www.androidbegin.com/tutorial/android-delete-multiple-selected-items-listview-tutorial/
 */

public class FriendActivity extends Activity {
    // Declare Variables
    ListView list;
    //ListViewAdapter listviewadapter;
    //List<Users> worldpopulationlist = new ArrayList<Uers>();
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

}
