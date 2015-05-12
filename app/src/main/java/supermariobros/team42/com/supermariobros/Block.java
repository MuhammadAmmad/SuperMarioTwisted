package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/11/2015.
 */
public abstract class Block
{
    private float x;
    private float y;

    private float width; // top left corner
    private float velocity;

    public Block()
    {
        width = SuperMarioSurfaceView.WIDTH / 15.0f;
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
