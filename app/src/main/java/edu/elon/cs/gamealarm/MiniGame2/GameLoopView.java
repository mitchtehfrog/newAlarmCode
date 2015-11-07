package edu.elon.cs.gamealarm.MiniGame2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Random;

import edu.elon.cs.gamealarm.R;

/**
 * Copyright JohnAnge Kernodle and Zack Layne
 */
public class GameLoopView extends SurfaceView implements SurfaceHolder.Callback {
    MediaPlayer player;
    private GameLoopThread thread;
    private SurfaceHolder surfaceHolder;
    private Context context;

    //locations
    private float ballX, ballY;
    private float spotX, spotY;
    private float spotX2, spotY2;
    private float spotX3, spotY3;

    private Point size;

    public GameLoopView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // remember the context for finding resources
        this.context = context;

        // want to know when the surface changes
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        // game loop thread
        thread = new GameLoopThread();


        // figure out the screen width
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        Random r = new Random();

        spotX = r.nextInt(size.x - 100)+50;
        spotY = r.nextInt((size.y/3)-100)+50;
        spotX2 = r.nextInt(size.x-100)+50;
        spotY2 = r.nextInt((size.y/3)-100)+50+(size.y/3);
        spotX3 = r.nextInt(size.x-100)+50;
        spotY3 = r.nextInt((size.y/3)-100)+50+(2*size.y/3);
        ballX = spotX;
        ballY = spotY;

    }


    // SurfaceHolder.Callback methods:
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // thread exists, but is in terminated state
        if (thread.getState() == Thread.State.TERMINATED) {
            thread = new GameLoopThread();
        }

        // start the game loop
        thread.setIsRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        thread.setIsRunning(false);

        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    // Game Loop Thread
    private class GameLoopThread extends Thread {

        private boolean isRunning = false;
        private long lastTime;

        // the bird sprite
        private Ball ball;

        //the spot sprite
        private Spot spot;
        private Spot spot2;
        private Spot spot3;
        private int greenSquareAmount = 0;

        private int hasSpot1 = 0;
        private int hasSpot2 = 0;
        private int hasSpot3 = 0;

        private Bitmap bgBitmap;

        // frames per second calculation
        private int frames;
        private long nextUpdate;

        public GameLoopThread() {
            ball = new Ball(context);

            spot = new Spot(context);
            spot2 = new Spot(context);
            spot3 = new Spot(context);
        }

        public void setIsRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        // the main loop
        @Override
        public void run() {
            player = MediaPlayer.create(context, R.raw.alarmnoise);
            player.start();
            lastTime = System.currentTimeMillis();

            while (isRunning) {

                // grab hold of the canvas
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas == null) {
                    // trouble -- exit nicely
                    isRunning = false;
                    continue;
                }

                synchronized (surfaceHolder) {

                    // compute how much time since last time around
                    long now = System.currentTimeMillis();
                    double elapsed = (now - lastTime) / 1000.0;
                    lastTime = now;

                    float screenX = size.x;
                    float screenY = size.y;

                    //top
                    if (ballY<=(screenX+40)-screenX){
                        ballY = 45;
                    }
                    else{
                        ballY += RollerBallActivity.accelY*3;
                    }
                    //left side
                    if (ballX<=(screenY+40)-screenY){
                        ballX=45;
                    }else{
                        ballX += RollerBallActivity.accelX*3;
                    }
                    //bottom
                    if (ballY>size.y-40){
                        ballY = size.y-45;
                    }
                    else{
                        ballY += RollerBallActivity.accelY*3;
                    }
                    //right side
                    if (ballX>size.x-40){
                        ballX=size.x-45;
                    }
                    else{
                        ballX += RollerBallActivity.accelX*3;
                    }

                    if ((Math.abs(ball.x-spotX)<20&&Math.abs(ball.y-spotY)<50)&& hasSpot1 == 0){
                        spot.changeImage(context);
                        greenSquareAmount++;
                        hasSpot1 = 1;
                        }
                    if ((Math.abs(ball.x-spotX2)<20&&Math.abs(ball.y-spotY2)<50)&& hasSpot2 == 0){
                        spot2.changeImage(context);
                        greenSquareAmount++;
                        hasSpot2 = 1;
                    }
                    if ((Math.abs(ball.x-spotX3)<20&&Math.abs(ball.y-spotY3)<50)&& hasSpot3 == 0){
                        spot3.changeImage(context);
                        greenSquareAmount++;
                        hasSpot3 = 1;
                    }

                    if (greenSquareAmount>2) {
                        player.stop();
                        ((Activity) context).finish();
                        System.out.println("complete");
                    }
                    // update/draw
                    doUpdate(elapsed);
                    doDraw(canvas);
                }


                // release the canvas
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

        // an approximate frames per second calculation
        private void updateFPS(long now) {
            float fps = 0.0f;
            ++frames;
            float overtime = now - nextUpdate;
            if (overtime > 0) {
                fps = frames / (1 + overtime/1000.0f);
                frames = 0;
                nextUpdate = System.currentTimeMillis() + 1000;
                System.out.println("FPS: " + (int) fps);
            }
        }

        /* THE GAME */

        // move all objects in the game
        private void doUpdate(double elapsed) {
            ball.doUpdate(elapsed, ballX, ballY);
            spot.doUpdate(elapsed, spotX, spotY);
            spot2.doUpdate(elapsed, spotX2, spotY2);
            spot3.doUpdate(elapsed, spotX3, spotY3);
        }

        // draw all objects in the game
        private void doDraw(Canvas canvas) {
            // draw the background

            canvas.drawColor(Color.WHITE);

            spot.doDraw(canvas);
            spot2.doDraw(canvas);
            spot3.doDraw(canvas);
            ball.doDraw(canvas);

        }
    }
}
