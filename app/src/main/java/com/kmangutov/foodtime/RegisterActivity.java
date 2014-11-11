package com.kmangutov.foodtime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jonathan on 11/11/2014.
 */
public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login_activity);
        setContentView(R.layout.register_activity);
    }


    public void register_submit_button(View v) {
        finish();

    }
}
