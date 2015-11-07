package edu.elon.cs.gamealarm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.view.View;

import java.util.Random;


public class Minigame1Activity extends Activity {

    private int shapeInt;
    MediaPlayer player;
    private ImageView circle;
    private ImageView circleOutline;

    private ImageView triangle;
    private ImageView triangleOutline;

    private ImageView square;
    private ImageView squareOutline;

    private ImageView oval;
    private ImageView ovalOutline;

    private ImageView trapezoid;
    private ImageView trapezoidOutline;

    private ImageView hexagon;
    private ImageView hexagonOutline;

    private int screenWidth, screenHeight;
    protected float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        player = MediaPlayer.create(Minigame1Activity.this, R.raw.alarmnoise);
        player.start();
        player.setLooping(true);
        //player.setLooping(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame1);

        // figure out the screen width
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x-200;
        screenHeight = size.y-200;

        Random random = new Random();

        //shapes
        circleOutline = (ImageView)findViewById(R.id.circleOutline);
        circleOutline.setImageResource(R.drawable.circleoutline);
        circleOutline.setX(random.nextInt(screenWidth));circleOutline.setY(random.nextInt(screenHeight));
        circle = (ImageView)findViewById(R.id.circle);
        circle.setImageResource(R.drawable.circle);
        circle.setX(random.nextInt(screenWidth));circle.setY(random.nextInt(screenHeight));

        triangleOutline = (ImageView)findViewById(R.id.triangleOutline);
        triangleOutline.setImageResource(R.drawable.triangleoutline);
        triangleOutline.setX(random.nextInt(screenWidth));triangleOutline.setY(random.nextInt(screenHeight));
        triangle = (ImageView)findViewById(R.id.triangle);
        triangle.setImageResource(R.drawable.triangle);
        triangle.setX(random.nextInt(screenWidth));triangle.setY(random.nextInt(screenHeight));

        squareOutline = (ImageView)findViewById(R.id.squareOutline);
        squareOutline.setImageResource(R.drawable.squareoutline);
        squareOutline.setX(random.nextInt(screenWidth));squareOutline.setY(random.nextInt(screenHeight));
        square = (ImageView)findViewById(R.id.square);
        square.setImageResource(R.drawable.square);
        square.setX(random.nextInt(screenWidth));square.setY(random.nextInt(screenHeight));

        ovalOutline = (ImageView)findViewById(R.id.ovalOutline);
        ovalOutline.setImageResource(R.drawable.ovaloutline);
        ovalOutline.setX(random.nextInt(screenWidth));ovalOutline.setY(random.nextInt(screenHeight));
        oval = (ImageView)findViewById(R.id.oval);
        oval.setImageResource(R.drawable.oval);
        oval.setX(random.nextInt(screenWidth));oval.setY(random.nextInt(screenHeight));

        trapezoidOutline = (ImageView)findViewById(R.id.trapezoidOutline);
        trapezoidOutline.setImageResource(R.drawable.trapezoidoutline);
        trapezoidOutline.setX(random.nextInt(screenWidth));trapezoidOutline.setY(random.nextInt(screenHeight));
        trapezoid = (ImageView)findViewById(R.id.trapezoid);
        trapezoid.setImageResource(R.drawable.trapezoid);
        trapezoid.setX(random.nextInt(screenWidth));trapezoid.setY(random.nextInt(screenHeight));

        hexagonOutline = (ImageView)findViewById(R.id.hexagonOutline);
        hexagonOutline.setImageResource(R.drawable.hexoutline);
        hexagonOutline.setX(random.nextInt(screenWidth));hexagonOutline.setY(random.nextInt(screenHeight));
        hexagon = (ImageView)findViewById(R.id.hexagon);
        hexagon.setImageResource(R.drawable.hex);
        hexagon.setX(random.nextInt(screenWidth));hexagon.setY(random.nextInt(screenHeight));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x = event.getX();
                y = event.getY();
                if(isPointInsideView(x, y, circle)){
                    shapeInt = 1;
                }else if (isPointInsideView(x, y, triangle)){
                    shapeInt = 2;
                }else if (isPointInsideView(x, y, square)){
                    shapeInt = 3;
                }else if (isPointInsideView(x, y, oval)){
                    shapeInt = 4;
                }else if (isPointInsideView(x, y, trapezoid)){
                    shapeInt = 5;
                }else if (isPointInsideView(x, y, hexagon)){
                    shapeInt = 6;
                }else {
                    shapeInt = 0;
                }
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                if (shapeInt == 1) {
                    circle.setX(event.getX());
                    circle.setY(event.getY());
                }else if (shapeInt == 2) {
                    triangle.setX(event.getX());
                    triangle.setY(event.getY());
                }else if (shapeInt == 3) {
                    square.setX(event.getX());
                    square.setY(event.getY());
                }else if (shapeInt == 4) {
                    oval.setX(event.getX());
                    oval.setY(event.getY());
                }else if (shapeInt == 5) {
                    trapezoid.setX(event.getX());
                    trapezoid.setY(event.getY());
                }else if (shapeInt == 6) {
                    hexagon.setX(event.getX());
                    hexagon.setY(event.getY());
                }
            }
            break;
            case MotionEvent.ACTION_UP: {
                if (shapeInt == 1) {
                    if ((Math.abs(circle.getX() - circleOutline.getX()) < 40) && (Math.abs(circle.getY() - circleOutline.getY()) < 40)) {
                        circle.setVisibility(View.INVISIBLE);
                        circleOutline.setImageResource(R.drawable.circle);
                    }
                } else if (shapeInt == 2){
                    if ((Math.abs(triangle.getX() - triangleOutline.getX()) < 40) && (Math.abs(triangle.getY() - triangleOutline.getY()) < 40)) {
                        triangle.setVisibility(View.INVISIBLE);
                        triangleOutline.setImageResource(R.drawable.triangle);
                    }
                }else if (shapeInt == 3){
                    if ((Math.abs(square.getX() - squareOutline.getX()) < 40) && (Math.abs(square.getY() - squareOutline.getY()) < 40)) {
                        square.setVisibility(View.INVISIBLE);
                        squareOutline.setImageResource(R.drawable.square);
                    }
                }else if (shapeInt == 4){
                    if ((Math.abs(oval.getX() - ovalOutline.getX()) < 40) && (Math.abs(oval.getY() - ovalOutline.getY()) < 40)) {
                        oval.setVisibility(View.INVISIBLE);
                        ovalOutline.setImageResource(R.drawable.oval);
                    }
                }else if (shapeInt == 5){
                    if ((Math.abs(trapezoid.getX() - trapezoidOutline.getX()) < 40) && (Math.abs(trapezoid.getY() - trapezoidOutline.getY()) < 40)) {
                        trapezoid.setVisibility(View.INVISIBLE);
                        trapezoidOutline.setImageResource(R.drawable.trapezoid);
                    }
                }else if (shapeInt == 6){
                    if ((Math.abs(hexagon.getX() - hexagonOutline.getX()) < 40) && (Math.abs(hexagon.getY() - hexagonOutline.getY()) < 40)) {
                        hexagon.setVisibility(View.INVISIBLE);
                        hexagonOutline.setImageResource(R.drawable.hex);
                    }
                }
                complete();
                shapeInt = 0;
            }
            break;
        }
        return true;
    }

    public static boolean isPointInsideView(float x, float y, View view){
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        //point is inside view bounds
        if(( x > viewX && x < (viewX + view.getWidth())) &&
                ( y > viewY && y < (viewY + view.getHeight()))){
            return true;
        } else {
            return false;
        }
    }

    public void complete()
    {
        if (circle.getVisibility() == View.INVISIBLE&&
                triangle.getVisibility() == View.INVISIBLE&&
                square.getVisibility() == View.INVISIBLE&&
                oval.getVisibility() == View.INVISIBLE&&
                trapezoid.getVisibility() == View.INVISIBLE&&
                hexagon.getVisibility() == View.INVISIBLE){
            player.setLooping(false);
            player.stop();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mini_game1, menu);
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
