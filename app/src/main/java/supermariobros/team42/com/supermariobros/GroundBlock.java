package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class GroundBlock extends Block
{

    public GroundBlock(float x, float y)
    {
        super(x,y);
        typeOfBlock = 2;
    }

    public void tick(Canvas c)
    {

            move();

    }
}
