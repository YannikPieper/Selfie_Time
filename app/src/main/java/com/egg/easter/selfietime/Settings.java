package com.egg.easter.selfietime;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Yannik Pieper on 16.05.2015.
 */

public class Settings extends Activity {
    private static SeekBar seek_bar;
    private static TextView text;
    private static Settings instance;
    private static int progressValue;
    private boolean on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        checkSwitch();
        SeekBar();

    }


    public void checkSwitch() { //Method to check the Switch

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final boolean tgpref = preferences.getBoolean("tgpref", true);  //default is true

        final Switch switch2 = (Switch) this.findViewById(R.id.switch2);
        switch2.setChecked(tgpref);
        on =! tgpref;

        seek_bar = (SeekBar) findViewById(R.id.seekBar);
        text = (TextView) findViewById(R.id.length);

        if(tgpref == false) {
            seek_bar.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
        }

        switch2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(on == false) {
                    seek_bar = (SeekBar) findViewById(R.id.seekBar);
                    text = (TextView) findViewById(R.id.length);

                    seek_bar.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    Settings.progressValue = 0;
                }

                if(on == true) {
                    seek_bar.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                }
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("tgpref", switch2.isChecked()); // value to store
                editor.commit();
                on =! on;
            }

        });
    }

    public void SeekBar(){ //Method to set up the Seek Bar
        seek_bar = (SeekBar) findViewById(R.id.seekBar);
        text = (TextView) findViewById(R.id.length);
        text.setText("Length: " + seek_bar.getProgress() + " / " + seek_bar.getMax());

        final int progress_value;
        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        Settings.progressValue = progress; //Set the progressValue Variable
                        text.setText("Length: " + progress + " / " + seek_bar.getMax());
                    }
                    @Override
                    public void onStartTrackingTouch (SeekBar seekBar) {

                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text.setText("Length: " + progress_value + " / " + seek_bar.getMax());

                    }

                }

        );


    }

    public int getProgressValue(){  //Set the progressValue
        return Settings.progressValue;
    }

    public static synchronized Settings getInstance(){ //Synchronize the Instance with the Variable
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }




    @Override
    public void onDestroy() { //destroy method for the class
        super.onDestroy();
    }
}
