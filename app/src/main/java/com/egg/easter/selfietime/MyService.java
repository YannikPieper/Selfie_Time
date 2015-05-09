package com.egg.easter.selfietime;

import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.CountDownTimer;
import 	java.util.Random;
import android.widget.Toast;
import android.app.Notification;
import java.lang.System;
import 	android.app.NotificationManager;
import android.app.PendingIntent;

public class MyService extends Service {
    private long randomNumber;
    private long minute = 1000;
    private int zahl = 1;
    private static final int NOTIFICATION_ID = 0;
    private boolean stop = false;

    /* Class for the countdown and the notification
     *
     *  */
    
    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flaggs, int startId) { //If the service class is created...

        this.counter(); //start the counter method
        return START_STICKY;

    }


    public void counter() {

            Random rand = new Random();
            randomNumber = (rand.nextInt(1) + 5) * minute;
            /*Creates a random number between 1 and 5 and multiplicate it with the minute variable (1000)
              Method to get a random number of minutes
             */

            new CountDownTimer(randomNumber, randomNumber) { //Countdown

                public void onTick(long number) {

                }

                    public void onFinish() { //Countdown has finished
                        if(stop == false){ //It's only to check if the switch button isn't toggled of
                        CharSequence text = "Take a Selfie!!!"; //Text shown by toast
                        int duration = Toast.LENGTH_SHORT; //Toast duration

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show(); //method to show the toast
                        setNotification(); //method to set the variable
                        counter(); //method to start the countdown again if stop is false
                    }

                }
            }.start();


}

    public void setNotification() { //set a notification for the user

        NotificationManager mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
          Notification n = new Notification();
          n.icon = R.drawable.ic_launcher; //Icon of the app which is shown by the notification
          n.tickerText = "Selfie Time!!!";
          n.when = System.currentTimeMillis();

        Intent selfie = new Intent(this, EasterEgg.class); //Method to create a new Class for the camera
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, selfie, 0);


         CharSequence contentText = "Press for Camera"; //Content of the Notification
          CharSequence contentTitle = "Selfie Time!";
        n.setLatestEventInfo(this, contentTitle, contentText, contentIntent);

        mNM.notify(NOTIFICATION_ID, n);
    }
    @Override
    public void onDestroy() { //destroy method for the service
        super.onDestroy();
        stop = true; //set stop to true so the counter will not start again
        this.stopSelf();
    }

}