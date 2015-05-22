package com.egg.easter.selfietime;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Class to open the Camera. When some one is pressing the notification,
 * this class will be created
 *
 * Created by jonathan on 09/04/15.
 */
public class EasterEgg extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myclass);

        capturePhoto(); //main method to open the camera
    }

        public void capturePhoto() {
            Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent,0);

        }
    }
}
