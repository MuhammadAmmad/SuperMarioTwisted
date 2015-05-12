package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;

/**
 * Created by Daniel on 5/11/2015.
 */
public class Ground implements TimeConscious {

    private float velocity;
    private float x;
    private float y;

    public Ground(float velocity, float x, float y)
    {
        this.velocity = velocity;
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

    }
}
