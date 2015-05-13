package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/11/2015.
 */
public abstract class Block
{
    private float x; // top left corner
    private float y;

    private float width;
    private float velocity;


    public Block(float x, float y)
    {
        width = SuperMarioSurfaceView.WIDTH / 15.0f;
        this.x = x;
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }



}
