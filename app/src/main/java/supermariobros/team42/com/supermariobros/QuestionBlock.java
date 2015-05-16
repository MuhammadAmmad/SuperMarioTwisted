package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class QuestionBlock extends Block
{
    private int item; // 0 for coin, 1 for fireflower, 2 for iceflower

    public QuestionBlock(float x, float y, int item, Player player)
    {
        super(x, y, player);
        this.item = item;
    }



    public void tick(Canvas c)
    {
        move(player);
    }
}
