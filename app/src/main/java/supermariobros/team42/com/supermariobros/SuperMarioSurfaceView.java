package supermariobros.team42.com.supermariobros;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    public static float BLOCKWIDTH;
    SuperMarioRenderThread renderThread;
    Paint paint = new Paint();
    Level1 testLevel;
    BitmapFactory.Options options = new BitmapFactory.Options();
    Bitmap beemush = BitmapFactory.decodeResource(getResources(), R.drawable.beemush, options);
    Bitmap blockused = BitmapFactory.decodeResource(getResources(), R.drawable.blockused, options);
    Bitmap blooper = BitmapFactory.decodeResource(getResources(), R.drawable.blooper, options);
    Bitmap boo = BitmapFactory.decodeResource(getResources(), R.drawable.boo, options);
    Bitmap bowser = BitmapFactory.decodeResource(getResources(), R.drawable.bowser, options);
    Bitmap bullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet, options);
    Bitmap chainchomp = BitmapFactory.decodeResource(getResources(), R.drawable.chainchomp, options);
    Bitmap groundblock = BitmapFactory.decodeResource(getResources(), R.drawable.groundblock, options);
    Bitmap ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground, options);



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
        BLOCKWIDTH = WIDTH / 15.0f;
        renderThread = new SuperMarioRenderThread(this);
        renderThread.start();
        testLevel = new Level1();

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
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        c.drawPaint(paint);
        Rect dst;

        for( Block b : testLevel.blockList )
        {
            dst = new Rect((int)b.getX(), (int) b.getY(), (int) (b.getX()+BLOCKWIDTH), (int) (b.getY()+ BLOCKWIDTH));
            c.drawBitmap(groundblock, null, dst, paint);
        }

        for( Ground g : testLevel.groundList)
        {
            dst = new Rect((int)g.getX(), (int) g.getY(), (int) (g.getX()+WIDTH), (int) (HEIGHT));
            c.drawBitmap(ground, null, dst, paint);
        }

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
