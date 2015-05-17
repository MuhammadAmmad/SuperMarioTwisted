package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

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



        if (movingRight || movingLeft || velocityY != 0)
        {
            for (Block b : l.blockList)
            {

                // walking in to block on left
                if (!jumping && !falling && x + SuperMarioSurfaceView.BLOCKWIDTH <= b.getX() + padding && x + SuperMarioSurfaceView.BLOCKWIDTH >= b.getX() - padding && y >= b.getY() && y <= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH)
                {
                    movingRight = false;
                }

                // walking into block on right
                else if (!jumping && !falling && x <= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH + padding && x >= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH - padding &&
                        y >= b.getY() && y <= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH)
                {
                    movingLeft = false;
                }
                // mario jumping and colliding with block on left
                else if (jumping && x + SuperMarioSurfaceView.BLOCKWIDTH <= b.getX() + padding && x + SuperMarioSurfaceView.BLOCKWIDTH >= b.getX() - padding && y >= b.getY() && y <= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH)

                {
                    jumping = false;
                    falling = true;
                    velocityY = 0;
                    movingRight = false;
                }
                // mario falling and colliding with block1 left side
                else if (falling && x + SuperMarioSurfaceView.BLOCKWIDTH <= b.getX() + padding && x + SuperMarioSurfaceView.BLOCKWIDTH >= b.getX() - padding && y >= b.getY() && y <= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH)
                {
                    movingRight = false;
                }

                // jumping colliding with right of block
                else if (jumping && x <= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH + padding && x >= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH - padding &&
                        y >= b.getY() && y <= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH)
                {
                    jumping = false;
                    falling = true;
                    velocityY = 0;
                    movingLeft = false;
                }

                // falling and colliding with right of block
                else if (falling && x <= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH + padding && x >= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH - padding &&
                        y >= b.getY() && y <= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH)
                {
                    movingLeft = false;
                }

                // falling on top of block
                else if (falling && y + SuperMarioSurfaceView.BLOCKWIDTH >= b.getY() - padding &&
                        y + SuperMarioSurfaceView.BLOCKWIDTH <= b.getY() + padding && x >= b.getX() && x <= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH)
                {
                    velocityY = 0.0f;
                    falling = false;

                    // make sure mario doesn't keep falling through block

                }

                // jumping below block
                else if (jumping && y <= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH + padding &&
                        y >= b.getY() + SuperMarioSurfaceView.BLOCKWIDTH - padding && x >= b.getX() && x <= b.getX() + SuperMarioSurfaceView.BLOCKWIDTH)
                {
                    velocityY = 0.0f;
                    jumping = false;
                    falling = true;
                }


            }
            velocityY += SuperMarioSurfaceView.g;
            y += velocityY;
        }


        if (y > SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH)
        {
            velocityY = 0.0f;
            y = SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH;
        }

        if (movingRight)
        {
            moveRight();
        }
        else if (movingLeft)
        {
            moveLeft();
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

}
