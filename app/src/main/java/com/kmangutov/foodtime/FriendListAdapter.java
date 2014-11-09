package com.kmangutov.foodtime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonathan on 11/8/2014.
 */

public class FriendListAdapter extends ArrayAdapter<User> {

    public ArrayList<User> userList;

    public FriendListAdapter(Context context, int textViewResourceId,
                               ArrayList<User> userList) {
        super(context, textViewResourceId, userList);
        this.userList = userList; /*new ArrayList<User>();
        this.userList.addAll(userList);*/
    }

    private class ViewHolder {
        TextView location;
        CheckBox name;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.friend_checkbox_layout, null);

            holder = new ViewHolder();
            holder.location = (TextView) convertView.findViewById(R.id.loc);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
            convertView.setTag(holder);

            holder.name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    User user = (User) cb.getTag();
                    /*Toast.makeText(getContext().getApplicationContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG
                    ).show();*/
                    user.setSelected(cb.isChecked());
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = userList.get(position);
        holder.location.setText(" (" + user.getLocation() + ")");
        holder.name.setText(user.getName());
        holder.name.setChecked(user.isSelected());
        holder.name.setTag(user);

        return convertView;

    }

}