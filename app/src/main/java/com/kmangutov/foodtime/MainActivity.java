package com.kmangutov.foodtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login_activity);
        setContentView(R.layout.login_activity);



        //input_start_time = (EditText) findViewById(R.id.inputStartTime);
        //input_end_time = (EditText) findViewById(R.id.inputEndTime);
        int start_hour;
        int start_minute;
        int end_hour;
        int end_minute;
        boolean start = false;
        boolean end = false;
        //listener for inputStartTime
      /*  input_start_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                //test for invalid time entries
                String test_time = charSequence.toString();
                if(test_time.length()>5)
                {
                    //invalid entry, clear text
                    input_start_time.setText("");
                    return;
                }

                int colon_placement = -1;
                for(int j = 0; j < test_time.length(); j++)
                {
                    if(charSequence.charAt(j)==':')
                    {
                        colon_placement = j;
                        break;
                    }
                }

                if(colon_placement>1 || colon_placement==-1)
                {
                    //invalid entry or no entry
                    input_start_time.setText("");
                    return;
                }


                String before_colon = test_time.substring(0,colon_placement);
                int before_colon_num = Integer.parseInt(before_colon);
                String after_colon = test_time.substring(colon_placement+1, test_time.length());
                int after_colon_num = Integer.parseInt(after_colon);

                if(before_colon_num < 0 || before_colon_num > 24 || after_colon_num <0 || after_colon_num >59)
                {
                    //invalid entry
                    input_start_time.setText("");
                    return;
                }
                else
                {
                    //create dummy time with start time

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //listener for inputEndTime
        input_end_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                //test for invalid time entries
                String test_time = charSequence.toString();
                if(test_time.length()>5)
                {
                    //invalid entry, clear text
                    input_end_time.setText("");
                    return;
                }

                int colon_placement = -1;
                for(int j = 0; j < test_time.length(); j++)
                {
                    if(charSequence.charAt(j)==':')
                    {
                        colon_placement = j;
                        break;
                    }
                }

                if(colon_placement>1 || colon_placement==-1)
                {
                    //invalid entry or no entry
                    input_end_time.setText("");
                    return;
                }


                String before_colon = test_time.substring(0,colon_placement);
                int before_colon_num = Integer.parseInt(before_colon);
                String after_colon = test_time.substring(colon_placement+1, test_time.length());
                int after_colon_num = Integer.parseInt(after_colon);

                if(before_colon_num < 0 || before_colon_num > 24 || after_colon_num <0 || after_colon_num >59)
                {
                    //invalid entry
                    input_end_time.setText("");
                    return;
                }
                else
                {
                    //create dummy time with end time
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
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

    public void login_button(View v){

        EditText username_input = (EditText)findViewById(R.id.username_input);
        EditText password_input = (EditText)findViewById(R.id.password_input);

        if(!username_input.getText().toString().trim().equals("") && !password_input.getText().toString().trim().equals(""))
        {
            //String output="";
            String password = password_input.getText().toString();
            String username = username_input.getText().toString();

            /* SQL query sample
            String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/get_user.php?username="+ username +"&password="+ password;
            Log.w("sRequest", sRequest);
            output = Process_request.runProcess(sRequest);
            */

            GlobalClass vars = (GlobalClass) getApplicationContext();
            ArrayList<User> userList = vars.getUserList();

            Log.w("debug", "ATTEMPTING LOGIN"+userList.size());

            for(int i=0; i<userList.size(); ++i) {
                Log.w("debug", userList.get(i).username +","+ userList.get(i).checkPassword(password));
                if (userList.get(i).username.compareTo(username)==0 && userList.get(i).checkPassword(password)) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    return;
                }
            }

            Toast.makeText(this, "Invalid Username and/or Password", Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(this, "Please fill in the Username and Password field.", Toast.LENGTH_SHORT).show();
    }

    public void new_user_button(View v) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

}
