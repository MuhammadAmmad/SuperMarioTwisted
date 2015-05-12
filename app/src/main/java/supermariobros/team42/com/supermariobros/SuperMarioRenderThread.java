package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Daniel on 5/11/2015.
 */
public class SuperMarioRenderThread extends Thread
{

    private final SuperMarioSurfaceView view;
    private static final int FRAME_PERIOD = 5; // ms

    public SuperMarioRenderThread(SuperMarioSurfaceView view)
    {
        this.view = view;
    }

    @Override
    public void run()
    {
        SurfaceHolder sh = view.getHolder();

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
                System.err.print(e.getStackTrace());
            }
            finally
            {
                if (c != null)
                {
                    sh.unlockCanvasAndPost(c);
                }
            }
            // Set the frame rate by setting this delay
            try
            {
                Thread.sleep(FRAME_PERIOD);
            }
            catch (InterruptedException e)
            {
                // This means that this thread was interrupted while sleeping.
                return;
            }
        }
    }
}