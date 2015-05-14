package com.egg.easter.selfietime;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;



public class MainActivity extends ActionBarActivity {

    private boolean start = false;
    private boolean isrunning = false;
    private int color;
    private RelativeLayout background;
    private int toastCounter = 1;
    private Switch switch1;
    private int duration;
    private Toast toast1;
    private Toast toast2;
    private int deathCounter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setContentView(R.layout.activity_main);

        final Intent intent1 = new Intent(getBaseContext(),MyService.class);
        final TextView textView = (TextView) this.findViewById(R.id.textView);
        background = (RelativeLayout) findViewById(R.id.background);

        final Switch switch1 = (Switch) this.findViewById (R.id.switch1);
        if(isrunning == false){
            switch1.setChecked(false);
        }

        switch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //OnClickListener for the switch

                if(start == false) { //check the state of the button

                    color++;
                    deathCounter++;
                    if (color == 9) {
                        background.setBackgroundColor(0xFF00FF00);
                        switch1.setText("Was denn jetzt?");
                    }
                    if (color == 12) {
                        background.setBackgroundColor(Color.RED);
                        switch1.setText("Entscheide dich!");
                    }
                    if (color == 15) {
                        background.setBackgroundColor(Color.MAGENTA);
                        switch1.setText("Macht dir das Spaß?");
                    }
                    if (color == 18) {
                        background.setBackgroundColor(Color.CYAN);
                        switch1.setText("Hab kein Bock mehr!");
                    }
                    if (color == 21) {
                        background.setBackgroundColor(Color.YELLOW);
                        switch1.setText("Jetzt hör halt mal auf!");
                    }
                    if (color == 24) {
                        background.setBackgroundColor(Color.parseColor("#d2691e"));
                        switch1.setText("Selten so eine unentschlossene Person gesehen...");
                    }
                    if (color == 27) {
                        background.setBackgroundColor(Color.parseColor("#ff1493"));
                        switch1.setText("Jetzt reichts aber echt!");
                    }
                    if (color == 30) {
                        background.setBackgroundColor(Color.parseColor("#eec900"));
                        switch1.setText("Ich will mich nicht mehr ändern :(");
                    }
                    if (color == 33) {
                        color = 8;
                    }
                    if(deathCounter == 50){
                        textView.setText("Mir wird schlecht...");
                    }
                    if (deathCounter == 100){
                        background.setBackgroundColor(Color.BLACK);
                        textView.setText("Look what you've done! I've lost my Switch, and my background... Live sucks! It's time for me to go, far, far away");
                        textView.setTextColor(Color.WHITE);
                        switch1.setVisibility(View.GONE); //Hide the switch button
                    }


                    Intent intent1 = new Intent(getBaseContext(), MyService.class); //Create a new service to start the selfie countdown
                    startService(intent1);

                    start = true;
                    isrunning = true;

                }
                else {

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
