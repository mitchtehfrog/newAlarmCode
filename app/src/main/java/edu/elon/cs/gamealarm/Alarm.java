package edu.elon.cs.gamealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

import java.util.Random;

import edu.elon.cs.gamealarm.MiniGame2.RollerBallActivity;

/**
 * Created by Michael on 10/22/2015.
 */
public class Alarm{

    private int hours, minutes;
    private boolean isOn = false;
    protected AlarmManager alarmManager;
    protected PendingIntent pendingIntent;

    public Alarm(Context context, long timeFromNow, int hours,  int minutes){
        Intent intent = null;
        this.hours = hours;
        this.minutes = minutes;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Random r = new Random();
        int num = r.nextInt(3);

        switch(num) {
            case 0:
                intent = new Intent(context,Minigame1Activity.class);
                break;

            case 1:
                intent = new Intent(context,RollerBallActivity.class);
                break;

            case 2:
                // this mini game doesn't run because of null pointer exception.
                intent = new Intent(context,MiniGame3Activity.class);
                break;
        }
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        AlarmManager.AlarmClockInfo info = new AlarmManager.AlarmClockInfo(timeFromNow, pendingIntent);

        Intent intent1 = new Intent(context,Minigame1Activity.class);
        Intent intent2 = new Intent(context,RollerBallActivity.class);
        Intent intent3 = new Intent(context,MiniGame3Activity.class);



        alarmManager.setAlarmClock(info, pendingIntent);
    }

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
