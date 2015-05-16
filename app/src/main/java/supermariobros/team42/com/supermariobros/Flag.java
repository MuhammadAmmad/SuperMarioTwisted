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
    private Player player;

    public Flag(Player player)
    {
        this.player = player;
    }

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
        if (player.isMovingLeft())
        {
            setX(getX() + 10.0f);
        }
        else if (player.isMovingRight())
        {
            setX(getX() - 10.0f);
        }
    }

}
