package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/11/2015.
 */
public abstract class Block implements TimeConscious
{
    private float x; // top left corner
    private float y;
    protected int typeOfBlock;
    private float width;
    private float velocity;


    public Block(float x, float y)
    {
        width = SuperMarioSurfaceView.WIDTH / 15.0f;
        this.x = x;
        this.y = y;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getVelocity()
    {
        return velocity;
    }

    public void setVelocity(float velocity)
    {
        this.velocity = velocity;
    }

    public void move()
    {

        // blocks move opposite of mario
        if (SuperMarioSurfaceView.player.isMovingLeft() && !Player.onRightOfBlock)
        {
            setX(getX() + 10.0f);
        }
        else if (SuperMarioSurfaceView.player.isMovingRight() && !Player.onLeftOfBlock)
        {
            setX(getX() - 10.0f);
        }

    }


}
