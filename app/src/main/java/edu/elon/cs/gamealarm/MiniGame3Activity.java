package edu.elon.cs.gamealarm;

/**
 * Created by zlayne on 11/5/2015.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

// Error occurs when this activity is called. Mini game will not run because of a null pointer exception.
public class  MiniGame3Activity extends Activity {
    MediaPlayer player;
    private Point size;

    private TextView countdown;
    private TextView memorizeText;
    private Button startButton;

    private Context context;

    // private final int NUM_NUMS = 6;

    // private int [] num = new int[NUM_NUMS];



    private int num1 = 0;
    private int num2 = 0;
    private int num3 = 0;
    private int num4 = 0;
    private int num5 = 0;
    private int num6 = 0;


    private int wrongInt1;
    private int wrongInt2;

    private Button b;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button done;

    private int bInt1;
    private int bInt2;

    private TextView num1TextView;
    private TextView num2TextView;
    private TextView num3TextView;
    private TextView num4TextView;
    private TextView num5TextView;
    private TextView num6TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(MiniGame3Activity.this, R.raw.alarmnoise);
        player.start();
        countdown = (TextView)findViewById(R.id.countDown);
        startButton = (Button)findViewById(R.id.startButton);
        memorizeText = (TextView)findViewById(R.id.memorizeText);

        num1TextView = (TextView)findViewById(R.id.number1TextView);
        num2TextView = (TextView)findViewById(R.id.number2TextView);
        num3TextView = (TextView)findViewById(R.id.number3TextView);
        num4TextView = (TextView)findViewById(R.id.number4TextView);
        num5TextView = (TextView)findViewById(R.id.number5TextView);
        num6TextView = (TextView)findViewById(R.id.number6TextView);

        b = (Button)findViewById(R.id.button);
        b2= (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        done = (Button)findViewById(R.id.doneButton);

        /*for (int i = 0; i < num.length; i++) {
            num[i] = new Random().nextInt(99);
        }*/


        num1 = new Random().nextInt(99);
        num2 = new Random().nextInt(9);
        num3 = new Random().nextInt(99);
        num4 = new Random().nextInt(9);
        num5 = new Random().nextInt(99);
        num6 = new Random().nextInt(9);


        num1TextView.setText(""+Integer.parseInt("n4m1"));
        num2TextView.setText(""+num2);
        num3TextView.setText(""+num3);
        num4TextView.setText(""+num4);
        num5TextView.setText(""+num5);
        num6TextView.setText(""+num6);
        randomWrongAnswers();

        // figure out the screen width
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        setViews();
    }

    // private double distance(int x1, int y1, int x2, int y2) {
    //    return (Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)));
    // }

    private final int MIN_DISTANCE = 30;

    public void setViews (){

       /* int x [] = new int[NUM_NUMS];
        int y [] = new int[NUM_NUMS];

        boolean isOK = true;
        int i = 0;
        while (i < num.length) {
            x[i] = new Random().nextInt(size.x/2);
            y[i] = new Random().nextInt(size.y/2)+350;

            for (int j = 0; j < i; j++) {
                if (distance(x[i], y[i], x[j], y[j]) < MIN_DISTANCE) {
                    isOK = false;
                }
            }

            if (isOK) {
                i++;
            }
        }*/


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(new Random().nextInt(size.x/2)+90, new Random().nextInt(size.y/2)+100, 0, 0);

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(new Random().nextInt(size.x/2)+90, new Random().nextInt(size.y/2)+100, 0, 0);

        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams3.setMargins(new Random().nextInt(size.x/2)+90, new Random().nextInt(size.y/2)+100, 0, 0);

        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams4.setMargins(new Random().nextInt(size.x/2)+90, new Random().nextInt(size.y/2)+100, 0, 0);

        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams5.setMargins(new Random().nextInt(size.x/2)+90, new Random().nextInt(size.y/2)+100, 0, 0);

        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams6.setMargins(new Random().nextInt(size.x/2)+90, new Random().nextInt(size.y/2)+100, 0, 0);

        num1TextView.setLayoutParams(layoutParams);
        num2TextView.setLayoutParams(layoutParams2);
        num3TextView.setLayoutParams(layoutParams3);
        num4TextView.setLayoutParams(layoutParams4);
        num5TextView.setLayoutParams(layoutParams5);
        num6TextView.setLayoutParams(layoutParams6);

        /*if ((Math.abs(num1TextView.getX()-num2TextView.getX())<20&&Math.abs(num1TextView.getY()-num2TextView.getY())<30)||
                (Math.abs(num1TextView.getX()-num3TextView.getX())<20&&Math.abs(num1TextView.getY()-num3TextView.getY())<30)||
                (Math.abs(num1TextView.getX()-num4TextView.getX())<20&&Math.abs(num1TextView.getY()-num4TextView.getY())<30)||
                (Math.abs(num1TextView.getX()-num5TextView.getX())<20&&Math.abs(num1TextView.getY()-num5TextView.getY())<30)||
                (Math.abs(num1TextView.getX()-num6TextView.getX())<20&&Math.abs(num1TextView.getY()-num6TextView.getY())<30)||
                (Math.abs(num2TextView.getX()-num3TextView.getX())<20&&Math.abs(num2TextView.getY()-num3TextView.getY())<30)||
                (Math.abs(num2TextView.getX()-num4TextView.getX())<20&&Math.abs(num2TextView.getY()-num4TextView.getY())<30)||
                (Math.abs(num2TextView.getX()-num5TextView.getX())<20&&Math.abs(num2TextView.getY()-num5TextView.getY())<30)||
                (Math.abs(num2TextView.getX()-num6TextView.getX())<20&&Math.abs(num2TextView.getY()-num6TextView.getY())<30)||
                (Math.abs(num3TextView.getX()-num4TextView.getX())<20&&Math.abs(num3TextView.getY()-num4TextView.getY())<30)||
                (Math.abs(num3TextView.getX()-num5TextView.getX())<20&&Math.abs(num3TextView.getY()-num5TextView.getY())<30)||
                (Math.abs(num3TextView.getX()-num6TextView.getX())<20&&Math.abs(num3TextView.getY()-num6TextView.getY())<30)||
                (Math.abs(num4TextView.getX()-num5TextView.getX())<20&&Math.abs(num4TextView.getY()-num5TextView.getY())<30)||
                (Math.abs(num4TextView.getX()-num6TextView.getX())<20&&Math.abs(num4TextView.getY()-num6TextView.getY())<30)||
                (Math.abs(num5TextView.getX()-num6TextView.getX())<20&&Math.abs(num5TextView.getY()-num6TextView.getY())<30)){
            setViews();
            System.out.println("here");
        }*/

    }


    public void start (View view){

        startButton.setVisibility(View.INVISIBLE);
        memorizeText.setVisibility(View.INVISIBLE);
        countdown.setVisibility(View.VISIBLE);
        num1TextView.setVisibility(View.VISIBLE);
        num2TextView.setVisibility(View.VISIBLE);
        num3TextView.setVisibility(View.VISIBLE);
        num4TextView.setVisibility(View.VISIBLE);
        num5TextView.setVisibility(View.VISIBLE);
        num6TextView.setVisibility(View.VISIBLE);

        new CountDownTimer(6000, 1) {

            public void onTick(long millisUntilFinished) {
                countdown.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                memorizeText.setText("Which numbers did you see?");
                countdown.setVisibility(View.INVISIBLE);
                memorizeText.setVisibility(View.VISIBLE);
                num1TextView.setVisibility(View.INVISIBLE);
                num2TextView.setVisibility(View.INVISIBLE);
                num3TextView.setVisibility(View.INVISIBLE);
                num4TextView.setVisibility(View.INVISIBLE);
                num5TextView.setVisibility(View.INVISIBLE);
                num6TextView.setVisibility(View.INVISIBLE);

                b.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);

                buttons();
            }
        }
                .start();
    }

    public void randomWrongAnswers (){
        Random r = new Random();
        int randNum = r.nextInt(1);
        if (randNum == 0) {
            wrongInt1 = r.nextInt(9);
            wrongInt2 = r.nextInt(99);
        }else{
            wrongInt1 = r.nextInt(99);
            wrongInt2 = r.nextInt(9);
        }

        if (wrongInt1 == num1 || wrongInt1 == num2 || wrongInt1 == num3 || wrongInt1 == num4 || wrongInt1 == num5 || wrongInt1 == num6 || wrongInt2 == num1 || wrongInt2 == num2 || wrongInt2 == num3 || wrongInt2 == num4 || wrongInt2 == num5 || wrongInt2 == num6){
            randomWrongAnswers();
        }
    }
    public void buttons (){
        bInt1 = new Random().nextInt(4)+1;
        bInt2 = new Random().nextInt(4)+1;

        if (bInt1 == bInt2){
            buttons();
        }
        switch (bInt1){
            case 1:
                b.setText(""+num1);
                break;
            case 2:
                b2.setText(""+num1);
                break;
            case 3:
                b3.setText(""+num1);
                break;
            case 4:
                b4.setText(""+num1);
                break;

        }
        switch (bInt2){
            case 1:
                b.setText(""+num2);
                break;
            case 2:
                b2.setText(""+num2);
                break;
            case 3:
                b3.setText(""+num2);
                break;
            case 4:
                b4.setText(""+num2);
                break;

        }

        if (b.getText() == ""){
            if (wrongInt1 != 101){
                b.setText(""+wrongInt1);
                wrongInt1 = 101;}else{
                b.setText(""+wrongInt2);
            }
        }
        if (b2.getText() == ""){
            if (wrongInt1 != 101){
                b2.setText(""+wrongInt1);
                wrongInt1 = 101;}else{
                b2.setText(""+wrongInt2);
            }
        }
        if (b3.getText() == ""){
            if (wrongInt1 != 101){
                b3.setText(""+wrongInt1);
                wrongInt1 = 101;}else{
                b3.setText(""+wrongInt2);
            }
        }
        if (b4.getText() == ""){
            if (wrongInt1 != 101){
                b4.setText(""+wrongInt1);
                wrongInt1 = 101;}else{
                b4.setText(""+wrongInt2);
            }
        }
    }
    public void check (View view){

        boolean b1correct = false;
        boolean b2correct = false;
        boolean b3correct = false;
        boolean b4correct = false;

        ColorDrawable buttonColor = (ColorDrawable) b.getBackground();
        int colorId = buttonColor.getColor();
        ColorDrawable buttonColor2 = (ColorDrawable) b2.getBackground();
        int colorId2 = buttonColor2.getColor();
        ColorDrawable buttonColor3 = (ColorDrawable) b3.getBackground();
        int colorId3 = buttonColor3.getColor();
        ColorDrawable buttonColor4 = (ColorDrawable) b4.getBackground();
        int colorId4 = buttonColor4.getColor();

        if (bInt1 == 1 && colorId == Color.BLUE){
            b1correct = true;
        }
        if (bInt1 == 2 && colorId2 == Color.BLUE){
            b2correct = true;
        }
        if (bInt1 == 3 && colorId3 == Color.BLUE){
            b3correct = true;
        }
        if (bInt1 == 4 && colorId4 == Color.BLUE){
            b4correct = true;
        }

        if (bInt2 == 1 && colorId == Color.BLUE){
            b1correct = true;
        }
        if (bInt2 == 2 && colorId2 == Color.BLUE){
            b2correct = true;
        }
        if (bInt2 == 3 && colorId3 == Color.BLUE){
            b3correct = true;
        }
        if (bInt2 == 4 && colorId4 == Color.BLUE){
            b4correct = true;
        }

        if (bInt1 != 1 && bInt2 != 1 && colorId == Color.RED){
            b1correct = true;
        }
        if (bInt1 != 2 && bInt2 != 2 && colorId2 == Color.RED){
            b2correct = true;
        }
        if (bInt1 != 3 && bInt2 != 3 && colorId3 == Color.RED){
            b3correct = true;
        }
        if (bInt1 != 4 && bInt2 != 4 && colorId4 == Color.RED){
            b4correct = true;
        }

        if (b1correct && b2correct && b3correct && b4correct){
            player.stop();
            finish();
            System.out.println("done");
        }else{
            System.out.println("wrong");
            onCreate(new Bundle());

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    MiniGame3Activity.this);
            alertDialog.setTitle("Try again!");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }
    }

    public void bPressed (View view){
        ColorDrawable buttonColor = (ColorDrawable) b.getBackground();
        int colorId = buttonColor.getColor();
        if (colorId != Color.BLUE) {
            b.setBackgroundColor(Color.BLUE);
        }else{
            b.setBackgroundColor(Color.RED);
        }
    }
    public void b2Pressed (View view){
        ColorDrawable buttonColor = (ColorDrawable) b2.getBackground();
        int colorId = buttonColor.getColor();
        if (colorId != Color.BLUE) {
            b2.setBackgroundColor(Color.BLUE);
        }else{
            b2.setBackgroundColor(Color.RED);
        }    }
    public void b3Pressed (View view){
        ColorDrawable buttonColor = (ColorDrawable) b3.getBackground();
        int colorId = buttonColor.getColor();
        if (colorId != Color.BLUE) {
            b3.setBackgroundColor(Color.BLUE);
        }else{
            b3.setBackgroundColor(Color.RED);
        }    }
    public void b4Pressed (View view){
        ColorDrawable buttonColor = (ColorDrawable) b4.getBackground();
        int colorId = buttonColor.getColor();
        if (colorId != Color.BLUE) {
            b4.setBackgroundColor(Color.BLUE);
        }else{
            b4.setBackgroundColor(Color.RED);
        }    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
