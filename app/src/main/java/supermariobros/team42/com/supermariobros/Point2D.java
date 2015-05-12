package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/11/2015.
 */
public class Point2D
{
    public int x;
    public int y;

    public Point2D( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Point2D( Point2D other ) {
        this.x = other.x;
        this.y = other.y;
    }
}
