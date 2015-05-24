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
    public static int gameState = -1; // 1 for level 1, etc. -1 for home screen or dead.
    public static float g = 2.3f;
    SuperMarioRenderThread renderThread;
    Paint paint = new Paint();
    Level1 level1;
    Level2 level2;
    Level3 level3;
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
    private Bitmap koopa;
    private Bitmap goldmario;
    private Bitmap L1;
    private Bitmap L2;
    private Bitmap L3;



    // rectangles
    Rect dst = new Rect();
    Rect leftButtonRect;
    Rect rightButtonRect;
    Rect aButtonRect;
    Rect bButtonRect;
    Rect marioRect;
    Rect bigMarioRect;
    Rect enemyRect;
    Rect L1but, L2but, L3but;

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
        bigMarioRect = new Rect();
        L1but = new Rect((int) (1.0f/4 * WIDTH),(int) (0.0f/8 * HEIGHT), (int) (3.0f/4 * WIDTH), (int) (2.0f/8 * HEIGHT));
        L2but = new Rect((int) (1.0f/4 * WIDTH),(int) (3.0f/8 * HEIGHT), (int) (3.0f/4 * WIDTH), (int) (5.0f/8 * HEIGHT));
        L3but = new Rect((int) (1.0f/4 * WIDTH),(int) (6.0f/8 * HEIGHT), (int) (3.0f/4 * WIDTH), (int) (8.0f/8 * HEIGHT));



        // decode bitmaps
        beemush = BitmapFactory.decodeResource(getResources(), R.drawable.beemush, options);
        blockused = BitmapFactory.decodeResource(getResources(), R.drawable.blockused, options);
        breakableblock = BitmapFactory.decodeResource(getResources(), R.drawable.breakableblock, options);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground, options);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba, options);
        koopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa, options);
        questionblock = BitmapFactory.decodeResource(getResources(), R.drawable.questionblock, options);
        flag = BitmapFactory.decodeResource(getResources(), R.drawable.flag, options);
        leftarrow = BitmapFactory.decodeResource(getResources(), R.drawable.leftarrow, options);
        rightarrow = BitmapFactory.decodeResource(getResources(), R.drawable.rightarrow, options);
        abutton = BitmapFactory.decodeResource(getResources(), R.drawable.abutton, options);
        bbutton = BitmapFactory.decodeResource(getResources(), R.drawable.bbutton, options);
        mario = BitmapFactory.decodeResource(getResources(), R.drawable.mario, options);
        breakableblock = BitmapFactory.decodeResource(getResources(), R.drawable.breakableblock, options);
        goldmario = BitmapFactory.decodeResource(getResources(), R.drawable.goldmario, options);
        L1 = BitmapFactory.decodeResource(getResources(), R.drawable.level1, options);
        L2 = BitmapFactory.decodeResource(getResources(), R.drawable.level2, options);
        L3 = BitmapFactory.decodeResource(getResources(), R.drawable.level3, options);


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
            case -1:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLUE);
                paint.setAntiAlias(true);
                c.drawPaint(paint);

                c.drawBitmap(L1, null, L1but, paint);
                c.drawBitmap(L2, null, L2but, paint);
                c.drawBitmap(L3, null, L3but, paint);



                break;


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
                    if (e.getType() == 1)
                    {
                        c.drawBitmap(goomba, null, dst, paint);
                    }
                    else if (e.getType() == 2)
                    {
                        c.drawBitmap(koopa, null, dst, paint);
                    }                    // }
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

                if(player.isSafe())
                {
                    paint.setAlpha(150);
                }
                else
                {
                    paint.setAlpha(255);
                }

                if(player.getSize() == 1 && !player.isInvincible())
                {
                    c.drawBitmap(mario, null, bigMarioRect, paint);

                }
                else if(player.getSize() == 1 && player.isInvincible())
                {
                    c.drawBitmap(goldmario, null, bigMarioRect, paint);
                }
                else if(player.getSize() == 0 && player.isInvincible())
                {
                    c.drawBitmap(goldmario, null, marioRect, paint);
                }
                else if(player.getSize() == 0 && !player.isInvincible())
                {
                    c.drawBitmap(mario, null, marioRect, paint);

                }
                break;

            case 2:

                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLUE);
                paint.setAntiAlias(true);
                c.drawPaint(paint);


                for (Block b : level2.blockList)
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


                for (Enemy e : level2.enemyList)
                {
                   /* if (e.getX() <= WIDTH && e.getX() >= -BLOCKWIDTH)
                    {*/
                    // c.drawBitmap(groundblock, null, dst, paint);
                    dst.set((int) e.getX(), (int) e.getY(), (int) (e.getX() + BLOCKWIDTH), (int) (e.getY() + BLOCKWIDTH));
                    // dst.set(200,200, 400, 400);
                    if (e.getType() == 1)
                    {
                        c.drawBitmap(goomba, null, dst, paint);
                    }
                    else if (e.getType() == 2)
                    {
                        c.drawBitmap(koopa, null, dst, paint);
                    }                    // }
                }

                for (Ground g : level2.groundList)
                {
                    if (g.getX() <= WIDTH && g.getX() >= -WIDTH)
                    {
                        dst.set((int) g.getX(), (int) g.getY(), (int) (g.getX() + WIDTH), (int) (HEIGHT));
                        c.drawBitmap(ground, null, dst, paint);
                    }
                }

                if (level2.flag.getX() <= WIDTH && level2.flag.getX() >= -BLOCKWIDTH)
                {
                    dst.set((int) level2.flag.getX(), (int) (2.0f * BLOCKWIDTH), (int) (level2.flag.getX() + BLOCKWIDTH), (int) (HEIGHT - BLOCKWIDTH));
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

                if(player.isSafe())
                {
                    paint.setAlpha(150);
                }
                else
                {
                    paint.setAlpha(255);
                }

                if(player.getSize() == 1 && !player.isInvincible())
                {
                    c.drawBitmap(mario, null, bigMarioRect, paint);

                }
                else if(player.getSize() == 1 && player.isInvincible())
                {
                    c.drawBitmap(goldmario, null, bigMarioRect, paint);
                }
                else if(player.getSize() == 0 && player.isInvincible())
                {
                    c.drawBitmap(goldmario, null, marioRect, paint);
                }
                else if(player.getSize() == 0 && !player.isInvincible())
                {
                    c.drawBitmap(mario, null, marioRect, paint);

                }
                break;

            case 3:

                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLUE);
                paint.setAntiAlias(true);
                c.drawPaint(paint);


                for (Block b : level3.blockList)
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


                for (Enemy e : level3.enemyList)
                {
                   /* if (e.getX() <= WIDTH && e.getX() >= -BLOCKWIDTH)
                    {*/
                    // c.drawBitmap(groundblock, null, dst, paint);
                    dst.set((int) e.getX(), (int) e.getY(), (int) (e.getX() + BLOCKWIDTH), (int) (e.getY() + BLOCKWIDTH));
                    // dst.set(200,200, 400, 400);
                    if (e.getType() == 1)
                    {
                        c.drawBitmap(goomba, null, dst, paint);
                    }
                    else if (e.getType() == 2)
                    {
                        c.drawBitmap(koopa, null, dst, paint);
                    }

                }

                for (Ground g : level3.groundList)
                {
                    if (g.getX() <= WIDTH && g.getX() >= -WIDTH)
                    {
                        dst.set((int) g.getX(), (int) g.getY(), (int) (g.getX() + WIDTH), (int) (HEIGHT));
                        c.drawBitmap(ground, null, dst, paint);
                    }
                }

                if (level3.flag.getX() <= WIDTH && level3.flag.getX() >= -BLOCKWIDTH)
                {
                    dst.set((int) level3.flag.getX(), (int) (2.0f * BLOCKWIDTH), (int) (level3.flag.getX() + BLOCKWIDTH), (int) (HEIGHT - BLOCKWIDTH));
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

                if(player.isSafe())
                {
                    paint.setAlpha(150);
                }
                else
                {
                    paint.setAlpha(255);
                }

                if(player.getSize() == 1 && !player.isInvincible())
                {
                    c.drawBitmap(mario, null, bigMarioRect, paint);

                }
                else if(player.getSize() == 1 && player.isInvincible())
                {
                    c.drawBitmap(goldmario, null, bigMarioRect, paint);
                }
                else if(player.getSize() == 0 && player.isInvincible())
                {
                    c.drawBitmap(goldmario, null, marioRect, paint);
                }
                else if(player.getSize() == 0 && !player.isInvincible())
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

        switch(gameState) {

            case 1:

                for (Block b : level1.blockList) {
                    b.tick(c);
                }


                for (Ground g : level1.groundList) {
                    g.tick(c);
                }


                for (Enemy e : level1.enemyList) {
                    e.tick(c);
                }

                level1.flag.tick(c);

                break;

            case 2:
                for (Block b : level2.blockList) {
                    b.tick(c);
                }


                for (Ground g : level2.groundList) {
                    g.tick(c);
                }


                for (Enemy e : level2.enemyList) {
                    e.tick(c);
                }

                level2.flag.tick(c);

                break;

            case 3:
                for (Block b : level3.blockList) {
                    b.tick(c);
                }


                for (Ground g : level3.groundList) {
                    g.tick(c);
                }


                for (Enemy e : level3.enemyList) {
                    e.tick(c);
                }

                level3.flag.tick(c);

                break;
        }



        marioRect.set((int) player.getX(), (int) player.getY(), (int) (player.getX() + BLOCKWIDTH), (int) (player.getY() + BLOCKWIDTH));
        bigMarioRect.set((int) (player.getX()-20.0f), (int) (player.getY() - 20.0f), (int) (player.getX() + BLOCKWIDTH + 20.0f), (int) (player.getY() + BLOCKWIDTH + 20.0f));
        renderGame(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        int action = e.getActionMasked();
        int pointerId1 = 0;
        int pointerId2 = 0;

        if(e.getPointerCount() == 1)
        {
            pointerId1 = e.getPointerId(0);
        }
        else if(e.getPointerCount() > 1)
        {
            if(e.getPointerId(1) == pointerId1)
                pointerId2 = e.getPointerId(0);
            else
                pointerId2 = e.getPointerId(1);

        }

        if (gameState == -1)
        {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (L1but.contains((int) e.getX(), (int) e.getY()))
                    {
                        level1 = new Level1();
                        player = new Player(level1);
                        gameState = 1;
                    }
                    else if (L2but.contains((int) e.getX(), (int) e.getY()))
                    {
                        level2 = new Level2();
                        player = new Player(level2);
                        gameState = 2;
                    }
                    else if (L3but.contains((int) e.getX(), (int) e.getY()))
                    {
                        level3 = new Level3();
                        player = new Player(level3);
                        gameState = 3;
                    }
                    else
                    {
                        // do nothing
                    }
                    break;
            }
        }
        else {
            switch (action) {


                case MotionEvent.ACTION_DOWN:
                    // check if left button is pressed
                    if (leftButtonRect.contains((int) e.getX(), (int) e.getY())) {
                        player.setMovingRight(false);
                        player.setMovingLeft(true);
                        player.setOnLeftOfBlock(false);
                        Log.d(TAG, "\nLeft button down");

                    }
                    if (rightButtonRect.contains((int) e.getX(), (int) e.getY())) {
                        player.setMovingRight(true);
                        player.setMovingLeft(false);
                        player.setOnRightOfBlock(false);
                        Log.d(TAG, "\nRight button down");

                    }
                    if (aButtonRect.contains((int) e.getX(), (int) e.getY()) && !player.isJumping() && !player.isFalling()) {
                        player.setJumping(true);
                        player.setFalling(false);
                        player.setOnTopOfBlock(false);
                        Log.d(TAG, "\nA button down");

                        player.jump();
                    }
                    if (bButtonRect.contains((int) e.getX(), (int) e.getY())) {
                        //player.action();
                        Log.d(TAG, "\nB button down");

                    }
                    break;

                case MotionEvent.ACTION_UP:
                    // check if left button is released

                    if (!leftButtonRect.contains((int) e.getX(), (int) e.getY()) && !aButtonRect.contains((int) e.getX(), (int) e.getY())) {
                        player.setMovingRight(false);
                        player.setMovingLeft(false);
                        Log.d(TAG, "\nLeft button up");

                    }
                    if (!rightButtonRect.contains((int) e.getX(), (int) e.getY()) && !aButtonRect.contains((int) e.getX(), (int) e.getY())) {
                        player.setMovingRight(false);
                        player.setMovingLeft(false);
                        Log.d(TAG, "\nRight button up");

                    }

                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    // check if left button is pressed
                    if (leftButtonRect.contains((int) e.getX(pointerId1), (int) e.getY(pointerId1))
                            || leftButtonRect.contains((int) e.getX(pointerId2), (int) e.getY(pointerId2)) ) {
                        player.setMovingRight(false);
                        player.setMovingLeft(true);
                        player.setOnLeftOfBlock(false);
                        Log.d(TAG, "\nLeft button down");

                    }
                    if (rightButtonRect.contains((int) e.getX(pointerId1), (int) e.getY(pointerId1))
                            || rightButtonRect.contains((int) e.getX(pointerId2), (int) e.getY(pointerId2))) {
                        player.setMovingRight(true);
                        player.setMovingLeft(false);
                        player.setOnRightOfBlock(false);
                        Log.d(TAG, "\nRight button down");

                    }
                    if (aButtonRect.contains((int) e.getX(pointerId1), (int) e.getY(pointerId1))
                            || aButtonRect.contains((int) e.getX(pointerId2), (int) e.getY(pointerId2)) && !player.isJumping() && !player.isFalling()) {
                        player.setJumping(true);
                        player.setFalling(false);
                        player.setOnTopOfBlock(false);
                        Log.d(TAG, "\nA button down");

                        player.jump();
                    }
                    if (bButtonRect.contains((int) e.getX(pointerId1), (int) e.getY(pointerId1))
                            || bButtonRect.contains((int) e.getX(pointerId2), (int) e.getY(pointerId2))) {
                        //player.action();
                        Log.d(TAG, "\nB button down");

                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    if (!(leftButtonRect.contains((int) e.getX(pointerId1), (int) e.getY(pointerId1))
                            || leftButtonRect.contains((int) e.getX(pointerId2), (int) e.getY(pointerId2)))) {
                        //  player.setMovingRight(false);
                        player.setMovingLeft(false);
                        Log.d(TAG, "\nLeft button up");

                    }
                    if (!(rightButtonRect.contains((int) e.getX(pointerId1), (int) e.getY(pointerId1))
                            || rightButtonRect.contains((int) e.getX(pointerId2), (int) e.getY(pointerId2)))) {
                        player.setMovingRight(false);
                        //  player.setMovingLeft(false);
                        Log.d(TAG, "\nRight button up");

                    }

                    break;
            }
        }




        return true;


    }
}