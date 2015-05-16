package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/11/2015.
 */
public abstract class Block implements TimeConscious
{
    private float x; // top left corner
    private float y;

    private float width;
    private float velocity;
    protected Player player;

    public Block(float x, float y, Player player)
    {
        width = SuperMarioSurfaceView.WIDTH / 15.0f;
        this.x = x;
        this.y = y;
        this.player = player;
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

    public void move(Player player)
    {

        // blocks move opposite of mario
        if (player.isMovingLeft())
        {
            setX(getX() + 10.0f);
        }
        else if (player.isMovingRight())
        {
            setX(getX() - 10.0f);
        }

    }


}
