package supermariobros.team42.com.supermariobros;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Daniel on 5/11/2015.
 */
public class SuperMarioSurfaceView extends SurfaceView implements SurfaceHolder.Callback, TimeConscious
{
    public static float WIDTH;
    public static float HEIGHT;
    public static float BLOCKWIDTH;
    public static float GROUNDHEIGHT;
    public static int gameState = 1; // 1 for playing, 0 for dead
    public static float g = 5.0f;
    private String TAG = "SuperMarioSurfaceView";
    SuperMarioRenderThread renderThread;
    Paint paint = new Paint();
    Level1 level1;
    public static Player player;
    private ArrayList<Bitmap> marioWalk = new ArrayList<Bitmap>();


    BitmapFactory.Options options = new BitmapFactory.Options();
    // load bitmaps
    Bitmap beemush = BitmapFactory.decodeResource(getResources(), R.drawable.beemush, options);
    Bitmap blockused = BitmapFactory.decodeResource(getResources(), R.drawable.blockused, options);
    /* Bitmap blooper = BitmapFactory.decodeResource(getResources(), R.drawable.blooper, options);
     Bitmap boo = BitmapFactory.decodeResource(getResources(), R.drawable.boo, options);
     Bitmap bowser = BitmapFactory.decodeResource(getResources(), R.drawable.bowser, options);
     Bitmap bullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet, options);
     Bitmap chainchomp = BitmapFactory.decodeResource(getResources(), R.drawable.chainchomp, options);*/ Bitmap breakableblock = BitmapFactory.decodeResource(getResources(), R.drawable.breakableblock, options);
    Bitmap ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground, options);
    /*Bitmap iceflower = BitmapFactory.decodeResource(getResources(), R.drawable.iceflower, options);
    Bitmap mariojump = BitmapFactory.decodeResource(getResources(), R.drawable.mariojump, options);
    Bitmap megamush = BitmapFactory.decodeResource(getResources(), R.drawable.megamush, options);
    Bitmap minimush = BitmapFactory.decodeResource(getResources(), R.drawable.minimush, options);
    Bitmap redshell = BitmapFactory.decodeResource(getResources(), R.drawable.redshell, options);
    Bitmap retro1up = BitmapFactory.decodeResource(getResources(), R.drawable.retro1up, options);
    Bitmap retro1up2 = BitmapFactory.decodeResource(getResources(), R.drawable.retro1up2, options);
    Bitmap retrofireflower = BitmapFactory.decodeResource(getResources(), R.drawable.retrofireflower, options);
    Bitmap retroflower = BitmapFactory.decodeResource(getResources(), R.drawable.retroflower, options);
    Bitmap retromario2 = BitmapFactory.decodeResource(getResources(), R.drawable.retromario2, options);
    Bitmap retromario3 = BitmapFactory.decodeResource(getResources(), R.drawable.retromario3, options);
    Bitmap retromush = BitmapFactory.decodeResource(getResources(), R.drawable.retromush, options);
    Bitmap retromush1 = BitmapFactory.decodeResource(getResources(), R.drawable.retromush1, options);
    Bitmap retromush2 = BitmapFactory.decodeResource(getResources(), R.drawable.retromush2, options);
    Bitmap retromush3 = BitmapFactory.decodeResource(getResources(), R.drawable.retromush3, options);
    Bitmap retromush4 = BitmapFactory.decodeResource(getResources(), R.drawable.retromush4, options);
    Bitmap starmush1 = BitmapFactory.decodeResource(getResources(), R.drawable.starmush1, options);
    Bitmap starmush2 = BitmapFactory.decodeResource(getResources(), R.drawable.starmush2, options);
    Bitmap upmush = BitmapFactory.decodeResource(getResources(), R.drawable.upmush, options);*/ Bitmap questionblock = BitmapFactory.decodeResource(getResources(), R.drawable.questionblock, options);
    Bitmap flag = BitmapFactory.decodeResource(getResources(), R.drawable.flag, options);
    Bitmap leftarrow = BitmapFactory.decodeResource(getResources(), R.drawable.leftarrow, options);
    Bitmap rightarrow = BitmapFactory.decodeResource(getResources(), R.drawable.rightarrow, options);
    Bitmap abutton = BitmapFactory.decodeResource(getResources(), R.drawable.abutton, options);
    Bitmap bbutton = BitmapFactory.decodeResource(getResources(), R.drawable.bbutton, options);
    Bitmap mario = BitmapFactory.decodeResource(getResources(), R.drawable.mario, options);


    Rect dst = new Rect();
    Rect leftButtonRect;
    Rect rightButtonRect;
    Rect aButtonRect;
    Rect bButtonRect;
    Rect marioRect;

    public SuperMarioSurfaceView(Context context)
    {
        super(context);
        // Notify the SurfaceHolder that you'd like to receive SurfaceHolder callbacks.
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        BLOCKWIDTH = WIDTH / 15.0f;
        GROUNDHEIGHT = HEIGHT - BLOCKWIDTH;

        // initialize all variables here
        renderThread = new SuperMarioRenderThread(this);
        renderThread.start();
        level1 = new Level1();
        player = new Player(level1);


        leftButtonRect = new Rect(0, (int) (4.0f / 5 * HEIGHT), (int) (1.0f / 5 * WIDTH), (int) HEIGHT);
        rightButtonRect = new Rect((int) (1.0f / 5 * WIDTH), (int) (4.0f / 5 * HEIGHT), (int) (2.0f / 5 * WIDTH), (int) HEIGHT);

        bButtonRect = new Rect((int) (5.5f / 8 * WIDTH), (int) (4.0f / 5 * HEIGHT), (int) (6.5f / 8 * WIDTH), (int) HEIGHT);
        aButtonRect = new Rect((int) (7.0f / 8 * WIDTH), (int) (4.0f / 5 * HEIGHT), (int) (WIDTH), (int) HEIGHT);
        marioRect = new Rect((int) player.getX(), (int) player.getY(), (int) (player.getX() + BLOCKWIDTH), (int) (GROUNDHEIGHT));

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        renderGame(canvas);
    }

    protected void renderGame(Canvas c)
    {

        switch (gameState)
        {
            case 1:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLUE);
                paint.setAntiAlias(true);
                c.drawPaint(paint);


                for (Block b : level1.blockList)
                {
                    // only draw the blocks on screen
                    if (b.getX() <= WIDTH && b.getX() >= -BLOCKWIDTH)
                    {
                        dst.set((int) b.getX(), (int) b.getY(), (int) (b.getX() + BLOCKWIDTH), (int) (b.getY() + BLOCKWIDTH));

                        if (b instanceof BreakableBlock)
                        {
                            c.drawBitmap(breakableblock, null, dst, paint);

                        }
                        else if (b instanceof QuestionBlock)
                        {
                            c.drawBitmap(questionblock, null, dst, paint);
                        }
                        else if (b instanceof GroundBlock)
                        {
                            c.drawBitmap(blockused, null, dst, paint);
                        }
                    }
                }

                for (Enemy e : level1.enemyList)
                {
                    if (e.getX() <= WIDTH && e.getX() >= -BLOCKWIDTH)
                    {
                        // c.drawBitmap(groundblock, null, dst, paint);
                        dst.set((int) e.getX(), (int) e.getY(), (int) (e.getX() + BLOCKWIDTH), (int) (e.getY() + BLOCKWIDTH));
                    }
                }

                for (Ground g : level1.groundList)
                {
                    if (g.getX() <= WIDTH && g.getX() >= -WIDTH)
                    {
                        dst.set((int) g.getX(), (int) g.getY(), (int) (g.getX() + WIDTH), (int) (HEIGHT));
                        c.drawBitmap(ground, null, dst, paint);
                    }
                }

                if (level1.flag.getX() <= WIDTH && level1.flag.getX() >= -BLOCKWIDTH)
                {
                    dst.set((int) level1.flag.getX(), (int) (2.0f * BLOCKWIDTH), (int) (level1.flag.getX() + BLOCKWIDTH), (int) (HEIGHT - BLOCKWIDTH));
                    c.drawBitmap(flag, null, dst, paint);
                }

                // draw on screen buttons
                paint.setAlpha(100);
                c.drawBitmap(leftarrow, null, leftButtonRect, paint);
                c.drawBitmap(rightarrow, null, rightButtonRect, paint);
                c.drawBitmap(abutton, null, aButtonRect, paint);
                c.drawBitmap(bbutton, null, bButtonRect, paint);
                paint.setAlpha(255);

                // draw mario

                c.drawBitmap(mario, null, marioRect, paint);
                break;

        }
    }

    @Override
    public void tick(Canvas c)
    {

        player.tick(c);

        for (Block b : level1.blockList)
        {
            b.tick(c);
        }


        for (Ground g : level1.groundList)
        {
            g.tick(c);
        }


        for (Enemy e : level1.enemyList)
        {
            e.tick(c);
        }

        level1.flag.tick(c);

        marioRect.set((int) player.getX(), (int) player.getY(), (int) (player.getX() + BLOCKWIDTH), (int) (player.getY() + BLOCKWIDTH));
        renderGame(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {

        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:


                // check if left button is pressed
                if (leftButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setMovingRight(false);
                    player.setMovingLeft(true);
                    player.setOnLeftOfBlock(false);
                    Log.d(TAG, "\nLeft button down");

                }
                else if (rightButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setMovingRight(true);
                    player.setMovingLeft(false);
                    player.setOnRightOfBlock(false);
                    Log.d(TAG, "\nRight button down");

                }
                else if (aButtonRect.contains((int) e.getX(), (int) e.getY()) && !player.isJumping() && !player.isFalling())
                {
                    player.setJumping(true);
                    player.setFalling(false);
                    player.setOnTopOfBlock(false);
                    Log.d(TAG, "\nA button down");

                    player.jump();
                }
                else if (bButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    //player.action();
                    Log.d(TAG, "\nB button down");

                }


                break;
            case MotionEvent.ACTION_UP:

                // check if left button is released

                if (!leftButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setMovingRight(false);
                    player.setMovingLeft(false);
                    Log.d(TAG, "\nLeft button up");

                }
                else if (!rightButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setMovingRight(false);
                    player.setMovingLeft(false);
                    Log.d(TAG, "\nRight button up");

                }
                else if (!aButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setJumping(false);


                }


                break;
        }
        return true;
    }


}
