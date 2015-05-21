package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SuperMarioRenderThread extends Thread
{
    private final SuperMarioSurfaceView view;
    private static final int FRAME_PERIOD = 1; // In ms

    public SuperMarioRenderThread(SuperMarioSurfaceView view)
    {
        this.view = view;
    }

    public void run()
    {
        SurfaceHolder sh = view.getHolder();
// Main game loop .
        while (!Thread.interrupted())
        {
            Canvas c = sh.lockCanvas(null);
            try
            {
                synchronized (sh)
                {
                    view.tick(c);
                }
            }
            catch (Exception e)
            {
            }
            finally
            {
                if (c != null)
                {
                    sh.unlockCanvasAndPost(c);
                }
            }
            try
            {
                Thread.sleep(FRAME_PERIOD);
            }
            catch (InterruptedException e)
            {
                return;
            }
        }
    }
}