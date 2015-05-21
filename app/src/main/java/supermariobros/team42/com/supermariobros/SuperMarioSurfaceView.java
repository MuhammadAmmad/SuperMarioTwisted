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
    public static String TAG = "SuperMario";
    public static float WIDTH;
    public static float HEIGHT;
    public static float BLOCKWIDTH;
    public static float GROUNDHEIGHT;
    public static int gameState = 1; // 1 for playing, 0 for dead
    public static float g = 3.3f;
    SuperMarioRenderThread renderThread;
    Paint paint = new Paint();
    Level1 level1;
    public static Player player;
    private ArrayList<Bitmap> marioWalk = new ArrayList<Bitmap>();


    BitmapFactory.Options options = new BitmapFactory.Options();

    // bitmaps

    private Bitmap beemush;
    private Bitmap blockused;
    private Bitmap ground;
    private Bitmap questionblock;
    private Bitmap flag;
    private Bitmap leftarrow;
    private Bitmap rightarrow;
    private Bitmap abutton;
    private Bitmap bbutton;
    private Bitmap mario;
    private Bitmap breakableblock;
    private Bitmap goomba;
    private Bitmap goldmario;

    // rectangles
    Rect dst = new Rect();
    Rect leftButtonRect;
    Rect rightButtonRect;
    Rect aButtonRect;
    Rect bButtonRect;
    Rect marioRect;
    Rect enemyRect;

    public SuperMarioSurfaceView(Context context)
    {
        super(context);
        // Notify the SurfaceHolder that you'd like to receive SurfaceHolder callbacks.
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        // start render thread
        renderThread = new SuperMarioRenderThread(this);
        renderThread.start();

        // initialize all variables here

        // constants
        WIDTH = getWidth();
        HEIGHT = getHeight();
        BLOCKWIDTH = WIDTH / 15.0f;
        GROUNDHEIGHT = HEIGHT - BLOCKWIDTH;

        options.inSampleSize = 2;
        level1 = new Level1();
        player = new Player(level1);
        leftButtonRect = new Rect(0, (int) (4.0f / 5 * HEIGHT), (int) (1.0f / 5 * WIDTH), (int) HEIGHT);
        rightButtonRect = new Rect((int) (1.0f / 5 * WIDTH), (int) (4.0f / 5 * HEIGHT), (int) (2.0f / 5 * WIDTH), (int) HEIGHT);
        bButtonRect = new Rect((int) (5.5f / 8 * WIDTH), (int) (4.0f / 5 * HEIGHT), (int) (6.5f / 8 * WIDTH), (int) HEIGHT);
        aButtonRect = new Rect((int) (7.0f / 8 * WIDTH), (int) (4.0f / 5 * HEIGHT), (int) (WIDTH), (int) HEIGHT);
        marioRect = new Rect((int) player.getX(), (int) player.getY(), (int) (player.getX() + BLOCKWIDTH), (int) (player.getY() + BLOCKWIDTH));

        // decode bitmaps
        beemush = BitmapFactory.decodeResource(getResources(), R.drawable.beemush, options);
        blockused = BitmapFactory.decodeResource(getResources(), R.drawable.blockused, options);
        breakableblock = BitmapFactory.decodeResource(getResources(), R.drawable.breakableblock, options);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground, options);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba, options);
        questionblock = BitmapFactory.decodeResource(getResources(), R.drawable.questionblock, options);
        flag = BitmapFactory.decodeResource(getResources(), R.drawable.flag, options);
        leftarrow = BitmapFactory.decodeResource(getResources(), R.drawable.leftarrow, options);
        rightarrow = BitmapFactory.decodeResource(getResources(), R.drawable.rightarrow, options);
        abutton = BitmapFactory.decodeResource(getResources(), R.drawable.abutton, options);
        bbutton = BitmapFactory.decodeResource(getResources(), R.drawable.bbutton, options);
        mario = BitmapFactory.decodeResource(getResources(), R.drawable.mario, options);
        breakableblock = BitmapFactory.decodeResource(getResources(), R.drawable.breakableblock, options);
        goldmario = BitmapFactory.decodeResource(getResources(), R.drawable.goldmario, options);

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
            case 0:
                level1 = new Level1();
                player = new Player(level1);
                gameState = 1;
                break;
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
                            if(((QuestionBlock)b).getUsed() )
                            {
                                c.drawBitmap(blockused, null, dst, paint);

                            }
                            else
                            {
                                c.drawBitmap(questionblock, null, dst, paint);

                            }
                        }
                        else if (b instanceof GroundBlock)
                        {
                            c.drawBitmap(blockused, null, dst, paint);
                        }
                    }
                }


                for (Enemy e : level1.enemyList)
                {
                   /* if (e.getX() <= WIDTH && e.getX() >= -BLOCKWIDTH)
                    {*/
                    // c.drawBitmap(groundblock, null, dst, paint);
                    dst.set((int) e.getX(), (int) e.getY(), (int) (e.getX() + BLOCKWIDTH), (int) (e.getY() + BLOCKWIDTH));
                    // dst.set(200,200, 400, 400);
                    c.drawBitmap(goomba, null, dst, paint);
                    // }
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

                if(player.timer < 400)
                {
                    paint.setAlpha(150);
                }
                else
                {
                    paint.setAlpha(255);
                }

                if(player.getSize() == 1)
                {
                    c.drawBitmap(goldmario, null, marioRect, paint);

                }
                else
                {
                    c.drawBitmap(mario, null, marioRect, paint);
                }
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
        int action = e.getActionMasked();

        switch(action)
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
                if (rightButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setMovingRight(true);
                    player.setMovingLeft(false);
                    player.setOnRightOfBlock(false);
                    Log.d(TAG, "\nRight button down");

                }
                if (aButtonRect.contains((int) e.getX(), (int) e.getY()) && !player.isJumping() && !player.isFalling())
                {
                    player.setJumping(true);
                    player.setFalling(false);
                    player.setOnTopOfBlock(false);
                    Log.d(TAG, "\nA button down");

                    player.jump();
                }
                if (bButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    //player.action();
                    Log.d(TAG, "\nB button down");

                }
                break;

            case MotionEvent.ACTION_UP:
                // check if left button is released

                if (!leftButtonRect.contains((int) e.getX(), (int) e.getY()) && !aButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setMovingRight(false);
                    player.setMovingLeft(false);
                    Log.d(TAG, "\nLeft button up");

                }
                if (!rightButtonRect.contains((int) e.getX(), (int) e.getY()) && !aButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    player.setMovingRight(false);
                    player.setMovingLeft(false);
                    Log.d(TAG, "\nRight button up");

                }
                if (!aButtonRect.contains((int) e.getX(), (int) e.getY()))
                {
                    //player.setJumping(false);


                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                // check if left button is pressed
                if (leftButtonRect.contains((int) e.getX(1), (int) e.getY(1)))
                {
                    player.setMovingRight(false);
                    player.setMovingLeft(true);
                    player.setOnLeftOfBlock(false);
                    Log.d(TAG, "\nLeft button down");

                }
                if (rightButtonRect.contains((int) e.getX(1), (int) e.getY(1)))
                {
                    player.setMovingRight(true);
                    player.setMovingLeft(false);
                    player.setOnRightOfBlock(false);
                    Log.d(TAG, "\nRight button down");

                }
                if (aButtonRect.contains((int) e.getX(1), (int) e.getY(1)) && !player.isJumping() && !player.isFalling())
                {
                    player.setJumping(true);
                    player.setFalling(false);
                    player.setOnTopOfBlock(false);
                    Log.d(TAG, "\nA button down");

                    player.jump();
                }
                if (bButtonRect.contains((int) e.getX(1), (int) e.getY(1)))
                {
                    //player.action();
                    Log.d(TAG, "\nB button down");

                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (!leftButtonRect.contains((int) e.getX(0), (int) e.getY(0)))
                {
                  //  player.setMovingRight(false);
                    player.setMovingLeft(false);
                    Log.d(TAG, "\nLeft button up");

                }
                if (!rightButtonRect.contains((int) e.getX(0), (int) e.getY(0)))
                {
                    player.setMovingRight(false);
                  //  player.setMovingLeft(false);
                    Log.d(TAG, "\nRight button up");

                }
                if (!aButtonRect.contains((int) e.getX(1), (int) e.getY(1)))
                {
                    //player.setJumping(false);


                }
                break;
        }




        return true;


    }
}