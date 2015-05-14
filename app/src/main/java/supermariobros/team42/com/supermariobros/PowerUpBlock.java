package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/13/2015.
 */
public class PowerUpBlock extends Block
{
    private int type; // 1 for fireflower



    private boolean smashed;

    public PowerUpBlock(float x, float y, int type)
    {
        super(x, y);
        this.type = type;
    }

    public void tick(Canvas c, boolean marioMoving)
    {

    }

    public int getSmashed()
    {
        return smashed;
    }

    public void setSmashed(int smashed)
    {
        this.smashed = smashed;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }


}
