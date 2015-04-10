package com.egg.easter.selfietime;

import android.app.Service;
import android.content.Intent;
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
    private int minute = 1000;
    private int zahl = 1;
    private static final int NOTIFICATION_ID = 0;

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flaggs, int startId) {

        this.counter();
        return START_STICKY;

    }


    public void counter() {

        Random rand = new Random();
        randomNumber = (rand.nextInt(55) + 5) * minute;

        new CountDownTimer(randomNumber, randomNumber) {

            public void onTick(long number) {

            }

            public void onFinish() {
                CharSequence text = "Take a Selfie!!!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
                setNotification();
                counter();
            }
        }.start();



}

    public void setNotification() {
        NotificationManager mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification n = new Notification();
        n.icon = R.drawable.ic_launcher;
        n.tickerText = "Selfie Time!!!";
        n.when = System.currentTimeMillis();

        Intent selfie = new Intent(this, EasterEgg.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, selfie, 0);

        CharSequence contentText = "Press for Camera";
        CharSequence contentTitle = "Selfie Time!";
        n.setLatestEventInfo(this, contentTitle, contentText, contentIntent);

        mNM.notify(NOTIFICATION_ID, n);
    }

    public void onDestroy() {
        super.onDestroy();
    }

}