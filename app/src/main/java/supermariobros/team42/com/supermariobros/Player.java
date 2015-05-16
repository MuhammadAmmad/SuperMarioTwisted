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
    private int timer = 0;


    public Player()
    {
        x = SuperMarioSurfaceView.WIDTH / 2 - SuperMarioSurfaceView.BLOCKWIDTH;
        y = SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH;
        absolutePositionX = x;

    }

    public void tick(Canvas c)
    {
        if (movingRight)
        {
            moveRight();
        }
        else if (movingLeft)
        {
            moveLeft();
        }


        velocityY += SuperMarioSurfaceView.g;
        y += velocityY;

        if( y > SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH )
        {
            velocityY = 0.0f;
            y = SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH;
        }



    }

    public void moveRight()
    {
        absolutePositionX += 10.0f;
    }

    public void moveLeft()
    {
        absolutePositionX -= 10.0f;
    }

    public void jump()
    {


        velocityY = -50.0f;


        // check for ground

        /*
        if (y + velocityY > SuperMarioSurfaceView.GROUNDHEIGHT)
        {
            y = SuperMarioSurfaceView.GROUNDHEIGHT;
        }
        else
        {
            y += velocityY;
        } */
    }

    public void fall()
    {
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
