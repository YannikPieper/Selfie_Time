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

    private boolean start = false;
    private boolean isrunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final Intent intent1 = new Intent(getBaseContext(),MyService.class);

        Switch switch1 = (Switch) this.findViewById (R.id.switch1);
        if(isrunning == false){
            switch1.setChecked(false);
        }


        switch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //OnClickListener for the switch

                if(start == false){ //check the state of the button


                CharSequence text = "Starting Selfie Time!"; //Text shown by the toast
                int duration = Toast.LENGTH_SHORT; //set the Toast duration to: short

                Toast toast = Toast.makeText(getApplicationContext(), text, duration); //Create the toast
                toast.show(); //show the toast

                Intent intent1 = new Intent(getBaseContext(),MyService.class); //Create a new service to start the selfie countdown
                startService(intent1);
                start = true;
                isrunning = true;

                }
                else {
                    CharSequence text = "Selfie Time Terminated"; //Text shown by the toast
                    int duration = Toast.LENGTH_SHORT; //set the Toast duration to: short

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);  //Create the toast
                    toast.show(); //show the toast
                    stopService(intent1); //command to stop the service in the background

                    start = false;
                    isrunning = false;
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
