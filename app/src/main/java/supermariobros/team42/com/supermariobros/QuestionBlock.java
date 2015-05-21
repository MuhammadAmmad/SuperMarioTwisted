package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class QuestionBlock extends Block
{
    private int item; // 0 for coin, 1 for fireflower, 2 for iceflower
    private boolean used;

    public QuestionBlock(float x, float y, int item)
    {
        super(x, y);
        this.item = item;
        typeOfBlock = 3;
    }



    public void tick(Canvas c)
    {
        move();
    }

    public boolean getUsed()
    {
        return used;
    }

    public void setUsed()
    {
        used = true;
    }
}
