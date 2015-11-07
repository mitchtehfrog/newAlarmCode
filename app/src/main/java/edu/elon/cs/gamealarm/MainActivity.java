package edu.elon.cs.gamealarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.elon.cs.gamealarm.MiniGame2.RollerBallActivity;

public class MainActivity extends Activity {

    private ListView listView;
    protected static List<Alarm> alarmArrayList;
    protected static int relevantPosition;
    protected static ArrayAdapter<Alarm> arrayAdapter;
    MediaPlayer player;
    private long timeFromNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        listView = (ListView) findViewById(R.id.alarmList);

        alarmArrayList = new ArrayList<Alarm>();

        //TODO: Remove - just for testing



        //TODO: code something that populates the array list with a file
        // Array list doesnt get reinflated if the alarm app is reloaded.

        arrayAdapter = new ArrayAdapter<Alarm>(this,
                R.layout.program_list,R.id.listtextview,
                alarmArrayList);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(itemClickListener);
        listView.setOnItemLongClickListener(itemLongClickListener);

    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Alarm current = alarmArrayList.get(position);
            if (current.isOn()) {
                current.setOn(false);
            }
            else
                current.setOn(true);
        }
    };

    AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            System.out.println("long click");
            relevantPosition = position;
            //Rachel: added null
            startEditOrDelete(null);
            return true;
        }
    };

    //Rachel changed this method to private and it now takes a view
    public void startEditOrDelete(View view){
        Intent intent = new Intent (this, EditOrDeleteActivity.class);
        startActivity(intent);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onAddClick(View view){
        Intent intent = new Intent (this, AddAlarmActivity.class);
        intent.putExtra("action", "add");
        startActivity(intent);
    }

    // RACHEL DID THIS ADD IT TO THE NEW CODE CHANGES
    //YOU CANNOT SWIPE IT
    // SWITCH DOESNT TURN THE ALARMS OFF ALARMS ARE ALWAYS ON.
    public void onSwitchClicked(View view) {
        boolean on = ((Switch) view).isChecked();

        if(on) {
            //code for if the switch is on to activate the alarms at the alotted time


            }
        }
            //code that turns off the switch & the alarm
        }




