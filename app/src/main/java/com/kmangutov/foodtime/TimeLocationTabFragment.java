package com.kmangutov.foodtime;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class TimeLocationTabFragment extends Fragment {


    private int index;

    public TimeLocationTabFragment() {
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
        View v = inflater.inflate(R.layout.fragment_time, null);
//        TextView tv = (TextView)v.findViewById(R.id.msg);
//        tv.setText("Time-Location Tab");

        return v;
    }


}
