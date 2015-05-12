package supermariobros.team42.com.supermariobros;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Daniel on 5/11/2015.
 */
public class SuperMarioSurfaceView extends SurfaceView implements SurfaceHolder.Callback, TimeConscious
{
    public static float WIDTH;
    public static float  HEIGHT;

    public SuperMarioSurfaceView(Context context)
    {
        super(context);
        // Notify the SurfaceHolder that you'd like to receive SurfaceHolder callbacks.
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        /*
         @Override
    public void surfaceCreated(SurfaceHolder holder) {
        renderThread = new DashTillPuffRenderThread(this);
        renderThread.start();
		background = new DashTillPuffBackground(this);
		trajectory = new Trajectory( this );
		factory    = new CosmicFactory(this, trajectory);
		bullet     = new Bullet(this);
    }
         */
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        renderGame(canvas);
    }

    protected void renderGame(Canvas c)
    {

    }

    @Override
    public void tick(Canvas c)
    {
        renderGame(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {

        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }


}
