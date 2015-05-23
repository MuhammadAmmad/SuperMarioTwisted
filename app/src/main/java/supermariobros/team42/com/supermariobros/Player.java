package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Daniel on 5/11/2015.
 */
public class Player implements TimeConscious
{
    private float velocityY;
    private float y;
    private final float x;
    private boolean movingRight;
    private boolean movingLeft;
    public static boolean onLeftOfBlock;
    public static boolean onRightOfBlock;
    private boolean invincible;
    private boolean safe = false;
    private boolean onTopOfBlock;
    private boolean hittingBlockFromBelow;
    private boolean falling;
    private boolean jumping;
    public int timer = 200;
    private boolean alive = true;
    private float padding = 5.0f;
    private Level l;
    private int size = 0; // 0 for small, 1 for big
    private TimerTask inTask;
    private TimerTask safeTask;
    private Timer pTimer = new Timer("powertimer");

    public Player(Level l)
    {
        x = SuperMarioSurfaceView.WIDTH / 2 - SuperMarioSurfaceView.BLOCKWIDTH;
        y = SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH;
        this.l = l;







    }

    public Level getLevel()
    {
        return l;
    }


    public void tick(Canvas c)
    {
        int col = -1;
        int eColi = -1;


        if (velocityY > 0.0f)
        {
            falling = true;
            jumping = false;
        }
        else if (velocityY < 0.0f)
        {
            jumping = true;
            falling = false;
        }

        if (onTopOfBlock)
        {
            falling = false;
        }

        enemyLoop:
        // check for collisions with enemies
        for (Enemy e : l.enemyList)
        {
            eColi = collision(e.getX(), e.getY());

            if ((eColi == 0 && !safe) || (eColi > 0 && invincible))
            {
                l.enemyList.remove(e);
            }
            else if (eColi > 0 && !safe)
            {
                if (size == 0 )
                {
                    SuperMarioSurfaceView.gameState = -1;
                }
                else if (size == 1 )
                {
                    size--; // make mario shrink
                    safe = true;
                    safeTask = new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            safe = false;
                        }
                    };
                    pTimer.schedule(safeTask, 3000);

                }

            }

        }

        loop1:
        // check for collisions with blocks
        for (Block b : l.blockList)
        {
            //check for falling off edge
            if (onTopOfBlock)
            {
                if (b.getX() >= x && (b.getX() + SuperMarioSurfaceView.BLOCKWIDTH) <= x)
                {
                    //do nothing yay!
                }
                else
                {
                    falling = true;
                    onTopOfBlock = false;
                }
            }
            col = collision(b.getX(), b.getY());

            if (col != -1)
            {
                switch (col)
                {
                    // colliding with top of block
                    case 0:
                        if (falling)
                        {
                            velocityY = 0.0f;
                            falling = false;
                            onTopOfBlock = true;


                        }
                        break loop1;
                    // colliding with right edge
                    case 1:
                        if (!jumping && !falling)
                        {
                            movingLeft = false;
                            onRightOfBlock = true;
                        }
                        // jumping colliding with right of block

                        else if (jumping)
                        {
                            movingLeft = false;
                            onRightOfBlock = true;
                        }
                        else if (falling)
                        {
                            movingLeft = false;
                            onRightOfBlock = true;
                        }
                        break loop1;
                    // bottom edge
                    case 2:
                        // jumping below block

                        if (jumping)
                        {
                            velocityY = 0.0f;
                            jumping = false;
                            falling = true;
                            hittingBlockFromBelow = true;

                            if (b instanceof BreakableBlock)
                            {
                                l.blockList.remove(b);
                            }
                            else if (b instanceof QuestionBlock && !((QuestionBlock) b).getUsed() && size == 0 && ((QuestionBlock) b).getType() == 1)
                            {
                                ((QuestionBlock) b).setUsed();
                                size++;
                            }
                            else if (b instanceof QuestionBlock && !((QuestionBlock) b).getUsed() && ((QuestionBlock) b).getType() == 2)
                            {
                                ((QuestionBlock) b).setUsed();
                                invincible = true;
                                inTask = new TimerTask()
                                {
                                    @Override
                                    public void run()
                                    {
                                        invincible = false;
                                    }
                                };
                                pTimer.schedule(inTask, 3000);

                            }


                        }
                        break loop1;


                    //left edge
                    case 3:
                        if (!jumping && !falling)
                        {
                            movingRight = false;
                            onLeftOfBlock = true;
                        }

                        // mario jumping and colliding with block on left
                        else if (jumping)
                        {
                            movingRight = false;
                            onLeftOfBlock = true;
                        }
                        // mario falling and colliding with block1 left side
                        else if (falling)
                        {
                            movingRight = false;
                            onLeftOfBlock = true;

                        }
                        break loop1;

                }


            }


        } // end for

        // check for flag collision
        if (x + SuperMarioSurfaceView.BLOCKWIDTH >= l.flag.getX())
        {
            SuperMarioSurfaceView.gameState = -1; // level completed
        }
        // check for vertical collision
        if (falling || jumping)
        {
            velocityY += SuperMarioSurfaceView.g;
            y += velocityY;
        }

        if (col != 3 && !onTopOfBlock)
        {
            onLeftOfBlock = false;
        }

        if (col != 1 && !onTopOfBlock)
        {
            onRightOfBlock = false;
        }


        if (y > SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH)
        {
            velocityY = 0.0f;
            falling = false;
            jumping = false;
            y = SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH;

        }


    }


    public void jump()
    {

        falling = false;
        jumping = true;
        velocityY = -40.0f;


    }

    public void fall()
    {
        jumping = false;
        falling = true;
        // implement terminal velocity
        velocityY = 15.0f;
        y += velocityY;
        // check for ground

        if (y + velocityY > SuperMarioSurfaceView.GROUNDHEIGHT)
        {
            y = SuperMarioSurfaceView.GROUNDHEIGHT;
        }
        else
        {
            y += velocityY;
        }
    }


    public void setOnLeftOfBlock(boolean onLeftOfBlock)
    {
        this.onLeftOfBlock = onLeftOfBlock;
    }


    public void setOnRightOfBlock(boolean onRightOfBlock)
    {
        this.onRightOfBlock = onRightOfBlock;
    }


    public float getY()
    {
        return y;
    }


    public float getX()
    {
        return x;
    }


    public boolean isFalling()
    {
        return falling;
    }

    public void setFalling(boolean falling)
    {
        this.falling = falling;
    }

    public boolean isJumping()
    {
        return jumping;
    }

    public void setJumping(boolean jumping)
    {
        this.jumping = jumping;
    }


    public boolean isMovingRight()
    {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight)
    {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft()
    {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft)
    {
        this.movingLeft = movingLeft;
    }


    public void setOnTopOfBlock(boolean onTopOfBlock)
    {
        this.onTopOfBlock = onTopOfBlock;
    }


    // returns -1 for no collision, 0 for top collision, 1 for right side, 2 for bottom, 3 for left side
    public int collision(float x1, float y1)
    {
        float padding = 30.0f;


        Rect left = new Rect((int) (x1 - (padding)), (int) (y1 - padding), (int) (x1 + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH + padding));
        Rect bot = new Rect((int) (x1 - padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH + padding));
        Rect right = new Rect((int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH - padding), (int) (y1 - padding), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH + padding));
        Rect top = new Rect((int) (x1 - SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (y1 - padding), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (y1 + padding));

        // check for collision in each region
        if (left.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH / 2)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with left");

            return 3;
        }
        else if (right.contains((int) (x), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH / 2)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with right");

            return 1;
        }
        else if (bot.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) y))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with bot");

            return 2;
        }
        else if (!onTopOfBlock && top.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with top");
            return 0;
        }
        else
        {
            return -1;
        }


    }

    public int getSize()
    {
        return size;
    }

    public boolean isInvincible()
    {
        return invincible;
    }

    public boolean isSafe()
    {
        return safe;
    }

}
