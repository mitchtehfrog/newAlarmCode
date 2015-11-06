package edu.elon.cs.gamealarm.MiniGame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;

import edu.elon.cs.gamealarm.R;

/**
 * Copyright JohnAnge Kernodle and Zack Layne
 */
public class Spot {
    protected float x, y;
    private float width, height;
    private Bitmap bitmap;

    private int screenWidth, screenHeight;

    private final float SCALE = 0.25f;

    public Spot(Context context) {

        // get the image
        System.out.println("here");
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.redsquare);

        // scale the size
        width = bitmap.getWidth() * SCALE;
        height = bitmap.getHeight() * SCALE;

        // figure out the screen width
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // start in top/left corner
        x = screenWidth/2;
        y = screenHeight/2;
    }

    public void doDraw(Canvas canvas) {
        // draw the bird
        canvas.drawBitmap(bitmap,
                null,
                new Rect((int) (x - width/2), (int) (y- height/2),
                        (int) (x + width/2), (int) (y + height/2)),
                null);
    }
    public void changeImage(Context context){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.greensquare);
    }

    public void doUpdate(double elapsed, float touchX ,float touchY) {
        // easing based on touch point
        x = (float) (x + ((touchX - x) * elapsed *2));
        y = (float) (y + ((touchY - y) * elapsed * 2));
    }
}
