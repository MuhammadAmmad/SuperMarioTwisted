package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class BreakableBlock extends Block
{
    private boolean smashed = false;


    public BreakableBlock(float x, float y, Player player)
    {
        super(x, y, player);
    }

    public void tick(Canvas c)
    {
        if (!smashed)
        {
            move(player);
        }
    }
}
