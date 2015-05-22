package supermariobros.team42.com.supermariobros;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Daniel on 5/11/2015.
 */
public class Enemy implements TimeConscious
{

    private float y;
    private float x;
    private boolean movingRight;
    private boolean movingLeft = true;
    public static boolean onLeftOfBlock;
    public static boolean onRightOfBlock;

    private boolean alive = true;
    private float padding = 5.0f;


    private int type; // 1 for koopa, 2 for goomba

    public Enemy(int type, float x, float y)
    {
        this.type = type;
        this.x = x;
        this.y = y;
    }


    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public void tick(Canvas c)
    {

        int eColi = -1;


        if (movingRight)
        {
            if (SuperMarioSurfaceView.player.isMovingLeft())
            {
                x += 13.0f;
            }
            else if (SuperMarioSurfaceView.player.isMovingRight())
            {
                x += -7.0f;

            }
            else
            {
                x += 3.0f;
            }
        }
        else if (movingLeft)
        {
            if (SuperMarioSurfaceView.player.isMovingLeft())
            {
                x += 7.0f;
            }
            else if (SuperMarioSurfaceView.player.isMovingRight())
            {
                x += -13.0f;

            }
            else
            {
                x += -3.0f;
            }

        }


        enemyLoop:
        for (Block b : SuperMarioSurfaceView.player.getLevel().blockList)
        {
            eColi = collision(b.getX(), b.getY());

            switch (eColi)
            {
                case 1:
                    // enemy is colliding with right side of block
                    movingRight = true;
                    movingLeft = false;
                    break enemyLoop;

                case 3:
                    // left side collision
                    movingRight = false;
                    movingLeft = true;
                    break enemyLoop;


            }
        }


    }

    public int collision(float x1, float y1)
    {
        float padding = 25.0f;


        Rect left = new Rect((int) (x1 - (padding)), (int) (y1 - padding), (int) (x1 + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH + padding));
        Rect bot = new Rect((int) (x1 - padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH + padding));
        Rect right = new Rect((int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH - padding), (int) (y1 - padding), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + padding), (int) (y1 + SuperMarioSurfaceView.BLOCKWIDTH + padding));
        Rect top = new Rect((int) (x1 - SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (y1 - padding), (int) (x1 + SuperMarioSurfaceView.BLOCKWIDTH + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (y1 + padding));

        // check for collision in each region
        if (left.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH / 4)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with left");

            return 3;
        }
        else if (right.contains((int) (x), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH / 2)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with right");

            return 1;
        }
        else if (bot.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) y))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with bot");

            return 2;
        }
        else if (top.contains((int) (x + SuperMarioSurfaceView.BLOCKWIDTH / 2), (int) (y + SuperMarioSurfaceView.BLOCKWIDTH)))
        {
            Log.d(SuperMarioSurfaceView.TAG, "Collided with top");
            return 0;
        }
        else
        {
            return -1;
        }


    }
}
