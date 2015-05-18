package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class Ground implements TimeConscious
{

    private float velocity;


    private float x;
    private float y;

    public Ground(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Ground()
    {
        velocity = 0.0f;
        x = 0.0f;
        y = 0.0f;
    }

    public void setVelocity(float v)
    {
        velocity = v;
    }

    @Override
    public void tick(Canvas c)
    {
        // blocks move opposite of mario
        if (SuperMarioSurfaceView.player.isMovingLeft() && !Player.onRightOfBlock  )
        {
            setX(getX() + 10.0f);
        }
        else if (SuperMarioSurfaceView.player.isMovingRight() && !Player.onLeftOfBlock )
        {
            setX(getX() - 10.0f);
        }
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getVelocity()
    {
        return velocity;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

}
