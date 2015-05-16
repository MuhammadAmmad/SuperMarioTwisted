package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class GroundBlock extends Block
{

    public GroundBlock(float x, float y, Player player)
    {
        super(x,y, player);
    }

    public void tick(Canvas c)
    {

            move(player);

    }
}
