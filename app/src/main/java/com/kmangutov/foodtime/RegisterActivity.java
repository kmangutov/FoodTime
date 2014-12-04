package com.kmangutov.foodtime;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jonathan on 11/11/2014.
 */
public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }


    public void register_submit_button(View v) {
        String username_input = ((EditText)findViewById(R.id.reg_username)).getText().toString();
        String password_input = ((EditText)findViewById(R.id.reg_password)).getText().toString();
        String fname_input = ((EditText)findViewById(R.id.reg_name1)).getText().toString();
        String lname_input = ((EditText)findViewById(R.id.reg_name3)).getText().toString();
        String location_input = ((EditText)findViewById(R.id.reg_location)).getText().toString();

        username_input=username_input.trim();
        password_input=password_input.trim();

        //Format Check
        if(username_input.equals("") || password_input.equals("") || fname_input.equals("") || lname_input.equals("") || location_input.equals(""))
        {
            Toast.makeText(this, "Please make sure that all fields are valid!", Toast.LENGTH_SHORT).show();
            return;
        }


        //Check for taken usernames
        GlobalClass vars = (GlobalClass) getApplicationContext();
        ArrayList<User> userList = vars.getUserList();
        for(int i=0; i<userList.size(); ++i) {
            Log.w("debug", userList.get(i).username+username_input);
            if (userList.get(i).username.compareTo(username_input)==0) {
                Toast.makeText(this, "Username already taken!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        vars.getUserList().add(new User(username_input,password_input,fname_input+" "+lname_input,location_input));

        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        finish();

    }
}
