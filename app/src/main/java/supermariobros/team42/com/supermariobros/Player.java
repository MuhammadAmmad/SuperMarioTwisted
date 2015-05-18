package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Daniel on 5/11/2015.
 */
public class Player implements TimeConscious
{
    private float velocityY;
    private float y;
    private final float x;
    private float absolutePositionX; // used to check if mario has reached edges of screen
    private boolean movingRight;
    private boolean movingLeft;
    public static boolean onLeftOfBlock;
    public static boolean onRightOfBlock;

    private boolean onTopOfBlock;
    private boolean hittingBlockFromBelow;
    private boolean falling;
    private boolean jumping;
    private boolean alive = true;
    private float padding = 5.0f;
    private Level l;


    public Player(Level l)
    {
        x = SuperMarioSurfaceView.WIDTH / 2 - SuperMarioSurfaceView.BLOCKWIDTH;
        y = SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH;
        absolutePositionX = x;
        this.l = l;

    }

    public void tick(Canvas c)
    {
        int col = -1;
        boolean collided = false;


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


        loop1:
        // check for collisions
        for (Block b : l.blockList)
        {
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


        // check for vertical collision
        if (falling || jumping)
        {
            velocityY += SuperMarioSurfaceView.g;
            y += velocityY;
        }

        if (col != 3)
        {
            onLeftOfBlock = false;
        }

        if (col != 1)
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


    public void moveRight()
    {
        absolutePositionX += 10.0f;
        movingRight = true;
        movingLeft = false;
    }

    public void moveLeft()
    {
        absolutePositionX -= 10.0f;
        movingLeft = true;
        movingRight = false;
    }

    public void jump()
    {

        falling = false;
        jumping = true;
        velocityY = -50.0f;


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


    public float getAbsolutePositionX()
    {
        return absolutePositionX;
    }

    public void setAbsolutePositionX(float absolutePositionX)
    {
        this.absolutePositionX = absolutePositionX;
    }

    public boolean isOnLeftOfBlock()
    {
        return onLeftOfBlock;
    }

    public void setOnLeftOfBlock(boolean onLeftOfBlock)
    {
        this.onLeftOfBlock = onLeftOfBlock;
    }

    public boolean isOnRightOfBlock()
    {
        return onRightOfBlock;
    }

    public void setOnRightOfBlock(boolean onRightOfBlock)
    {
        this.onRightOfBlock = onRightOfBlock;
    }

    public Level getL()
    {
        return l;
    }

    public void setL(Level l)
    {
        this.l = l;
    }

    public float getPadding()
    {
        return padding;
    }

    public void setPadding(float padding)
    {
        this.padding = padding;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getVelocityY()
    {
        return velocityY;
    }

    public void setVelocityY(float velocityY)
    {
        this.velocityY = velocityY;
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

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
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


    public boolean isOnTopOfBlock()
    {
        return onTopOfBlock;
    }

    public void setOnTopOfBlock(boolean onTopOfBlock)
    {
        this.onTopOfBlock = onTopOfBlock;
    }

    public boolean isHittingBlockFromBelow()
    {
        return hittingBlockFromBelow;
    }

    public void setHittingBlockFromBelow(boolean hittingBlockFromBelow)
    {
        this.hittingBlockFromBelow = hittingBlockFromBelow;
    }

    // returns -1 for no collision, 0 for top collision, 1 for right side, 2 for bottom, 3 for left side
    public int collision(float x1, float y1)
    {
        float padding = 25.0f;


        Rect left = new Rect((int) (x1 - padding), (int) y1, (int) (x1), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH));
        Rect bot = new Rect((int) (x1 - padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH + padding));
        Rect right = new Rect((int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH), (int) y1, (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH));
        Rect top = new Rect((int) (x1 - padding), (int) (y1 - padding), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + padding), (int) (y1));

        // check for collision in each region
        if (left.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH / 2)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with left");

            return 3;
        }
        else if (right.contains((int) (x), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with right");

            return 1;
        }
        else if (bot.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) y))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with bot");

            return 2;
        }
        else if (top.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with top");
            return 0;
        }
        else
        {
            return -1;
        }
    }


}
