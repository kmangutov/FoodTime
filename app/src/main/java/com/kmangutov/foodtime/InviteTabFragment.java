package com.kmangutov.foodtime;



import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class InviteTabFragment extends Fragment {


    private int index;
    FriendListAdapter adapter = null;

    public InviteTabFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        Bundle data = getArguments();
//        index = data.getInt("idx");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friend, null);
        showListView(v);

        return v;
    }

    public void showListView(View v) {

        GlobalClass vars = (GlobalClass) getActivity().getApplicationContext();

        //ArrayAdapter for friendList array
        ListView myListView = (ListView) getActivity().findViewById(R.id.friendInviteListView);
        adapter = new FriendListAdapter(getActivity(),
                R.layout.friend_checkbox_layout, vars.getUser().getFriendList());
        ListView listView = (ListView) v.findViewById(R.id.friendInviteListView);

        listView.setAdapter(adapter);
        listView.smoothScrollToPosition(0);
        adapter.notifyDataSetChanged();

//        //What do we do when an item in list is clicked?
//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Open the User's info page
//                //selected_name = ((TextView)view).getText().toString();
//                //Unimplemented
//            }
//        });
    }



}
