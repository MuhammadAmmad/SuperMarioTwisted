package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/13/2015.
 */
public class Flag implements TimeConscious
{
    private float x;



    private float y;
    private boolean activated;


    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

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



}
