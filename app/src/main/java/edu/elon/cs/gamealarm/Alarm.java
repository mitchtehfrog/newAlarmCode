package edu.elon.cs.gamealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

/**
 * Created by Michael on 10/22/2015.
 */
public class Alarm{

    private int hours, minutes;
    private boolean isOn = false;

    public Alarm(Context context, long timeFromNow, int hours,  int minutes){
        this.hours = hours;
        this.minutes = minutes;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,Minigame1Activity.class); //testing for something
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        AlarmManager.AlarmClockInfo info = new AlarmManager.AlarmClockInfo(timeFromNow, pendingIntent);
        alarmManager.setAlarmClock(info, pendingIntent);
    }

//    @Override
//    public void onReceive(Context context, Intent intent){
//        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        // AlarmManager.AlarmClockInfo info = new AlarmManager.AlarmClockInfo(System.currentTimeMillis(), pendingIntent);
//        // manager.setAlarmClock(info, pendingIntent);
//        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
//        wakeLock.acquire();
//
//
//        Intent i = new Intent(context, Minigame1Activity.class);
//        //startActivity(intent);
//
//        wakeLock.release();
//    }


    //public void setAlarm(Context context){
      //  AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
       // Intent intent = new Intent(context, Alarm.class);
       // PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        //alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo, pendingIntent );
   // }

    public void setOn(boolean isOn){
        this.isOn = isOn;
    }

    public boolean isOn(){
        return isOn;
    }

    public void setHours(int hours){
        this.hours = hours;
    }

    public int getHours(){
        return hours;
    }

    public void setMinutes (int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes () {
        return minutes;
    }

        @Override
        public String toString() {
            String alarm = hours + ":";

            if (minutes < 10){
                alarm += "0";
            }

            alarm += minutes + " ";
            return alarm;
    }

}
