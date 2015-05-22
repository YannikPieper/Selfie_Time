package com.egg.easter.selfietime;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
    private int first = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void openSettings() {

        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setContentView(R.layout.activity_main); //Get access to the layout file activity_main to change the settings

        final Intent intent1 = new Intent(getBaseContext(),MyService.class);
        final TextView textView = (TextView) this.findViewById(R.id.textView);
        background = (RelativeLayout) findViewById(R.id.background);

        final Switch switch1 = (Switch) this.findViewById (R.id.switch1); //Get the switch

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final boolean tgpref = preferences.getBoolean("tgpref", true);  //default is true

        if(first == 0){
            switch1.setChecked(false);
        }
        if(first == 1){
            switch1.setChecked(tgpref);
        }
        start = tgpref;

        switch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //OnClickListener for the switch

                if(start == false) { //check the state of the button

                    color++; //variable color +1
                    deathCounter++; //variable deathCounter +1
                    if (color == 9) {  //If the user switch the switch 9 times...
                        background.setBackgroundColor(0xFF00FF00); //change background Color and text (Easter Egg)
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
                    if(deathCounter == 50){ //Selfie Time text changes
                        textView.setText("Mir wird schlecht...");
                    }
                    if (deathCounter == 100){
                        background.setBackgroundColor(Color.BLACK); //Set the background Color to black
                        textView.setText("Look what you've done! I've lost my Switch, and my background... Live sucks! It's time for me to go, far, far away"); //Change the text
                        textView.setTextColor(Color.WHITE); //Change the Text Color
                        switch1.setVisibility(View.GONE); //Hide the switch button
                    }

                    Intent intent1 = new Intent(getBaseContext(), MyService.class); //Create a new service to start the selfie countdown
                    startService(intent1); //start the new service
                }
                else {
                    stopService(intent1); //command to stop the service in the background

                }

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("tgpref", switch1.isChecked()); // value to store
                editor.commit();
                start =! start;
                first = 1;
            }});
        return true;
        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
           case R.id.action_settings:
               openSettings();
               return true;
           default:
               return super.onOptionsItemSelected(item);
        }
    }
}
