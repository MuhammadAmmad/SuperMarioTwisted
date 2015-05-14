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
    public static boolean movingRight;
    public static boolean movingLeft;
    private boolean falling;
    private boolean jumping;
    private boolean alive = true;

    public Player()
    {
        x = SuperMarioSurfaceView.WIDTH / 5.0f;
    }

    public void tick(Canvas c)
    {
        if (falling)
        {

            // implement terminal velocity
            if (!(velocityY + SuperMarioSurfaceView.g > 15.0f))
            {
                velocityY = velocityY + SuperMarioSurfaceView.g;
            }
            else
            {
                velocityY = 15.0f;
            }

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

        if (jumping)
        {
            // implement terminal velocity
            if (!(velocityY - SuperMarioSurfaceView.g < -15.0f))
            {
                velocityY = velocityY + SuperMarioSurfaceView.g;
            }
            else
            {
                velocityY = -15.0f;
            }

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

}
