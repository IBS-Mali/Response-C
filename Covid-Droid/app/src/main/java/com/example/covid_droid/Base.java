package com.example.covid_droid;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by fad on 09/11/14.
 */
public class Base extends Activity {

    private final static String TAG = "AbolaLog-BaseActivity";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void callUrgencyDialog(Activity activity) {

        final Map<String, String> map = new HashMap<String, String>();
        String orang = getString(R.string.orange);
        String mltel = getString(R.string.malitel);
        map.put(mltel, getString(R.string.num_malitel));
        map.put(orang, getString(R.string.num_orange));
        final CharSequence[] items = {mltel, orang};

        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.num_orange)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PRIVILEGED) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                startActivity(callIntent);
            }
        }
    }

}
