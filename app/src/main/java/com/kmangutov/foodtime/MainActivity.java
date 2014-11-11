package com.kmangutov.foodtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

            /*
            String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/get_user.php?username="+ username +"&password="+ password;
            Log.w("sRequest", sRequest);
            output = Process_request.runProcess(sRequest);
            */

            if(!username_input.equals("")&&!password_input.equals(""))
            {
                //set_user(username, password);

                //Generate fake user db and friendlist (temporary)
                GlobalClass vars = (GlobalClass) getApplicationContext();
                vars.getFriendList().clear();
                vars.getUserList().clear();

                ArrayList<User> userList = vars.getUserList();
                userList.add(new User("smthn4","Jon", "Urbana, IL"));
                userList.add(new User("smthn5","Borg", "Urbana, IL"));
                userList.add(new User("smthn6","Harrison", "Champaign, IL"));
                userList.add(new User("smthn","Kirill", "Urbana, IL"));
                userList.add(new User("smthn2","Isra", "FLORIDAAAAAA"));
                userList.add(new User("smthn3","Xi", "Champaign, IL"));

                ArrayList<User> friendList = vars.getFriendList();
                int i= 3;
                while(i<userList.size()) {
                    friendList.add(userList.get(i));
                    ++i;
                }

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
            else
            {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }


        }
        else
            Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show();
    }

    public void new_user_button(View v) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}
