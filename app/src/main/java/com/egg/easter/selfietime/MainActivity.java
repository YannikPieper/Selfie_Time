package com.egg.easter.selfietime;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.view.View;
import android.widget.Toast;
import 	android.content.Intent;

public class MainActivity extends ActionBarActivity {

    private boolean start = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        Switch switch1 = (Switch) this.findViewById (R.id.switch1);
        switch1.setChecked(true);
        switch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(start == false){


                CharSequence text = "Starting Selfie Time!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();

                startService(new Intent(getBaseContext(),MyService.class));

                start = true;
                }
                else {
                    CharSequence text = "Selfie Time Terminated";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();

                    start = false;
                }


            }});


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
