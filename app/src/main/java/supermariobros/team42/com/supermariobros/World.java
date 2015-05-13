package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class World implements TimeConscious
{
    public static final float g = 9.8f;
    Player player;

    public World()
    {
        player = new Player();
    }

    @Override
    public void tick(Canvas c)
    {

    }

    public void render(Canvas c)
    {

    }
}
