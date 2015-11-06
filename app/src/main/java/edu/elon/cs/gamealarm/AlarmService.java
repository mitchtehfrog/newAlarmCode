package edu.elon.cs.gamealarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.Toast;

/**
 * Created by mthompson31 on 11/6/2015.
 */
public class AlarmService extends Service {


    @Override
    public IBinder onBind(Intent intent){
        return null;
    }



}
