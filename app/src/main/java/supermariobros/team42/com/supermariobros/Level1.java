package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/12/2015.
 */
public class Level1 extends Level
{
    public float BLOCKHEIGHT = 4.0f* SuperMarioSurfaceView.HEIGHT/10.0f;
    public float WIDTH = SuperMarioSurfaceView.WIDTH;
    public float HEIGHT = SuperMarioSurfaceView.HEIGHT;

    public Level1()
    {
        // create the level here
        groundList.add(new Ground(0.0f, 0.9f*HEIGHT));
        blockList.add(new BreakableBlock(4.0f * SuperMarioSurfaceView.WIDTH / 15.0f , BLOCKHEIGHT ));
        blockList.add(new BreakableBlock(5.0f * SuperMarioSurfaceView.WIDTH / 15.0f , BLOCKHEIGHT));
        blockList.add(new BreakableBlock(6.0f * SuperMarioSurfaceView.WIDTH / 15.0f , BLOCKHEIGHT));


    }
}
