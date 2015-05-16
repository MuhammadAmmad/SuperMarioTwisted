package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/12/2015.
 */
public class Level1 extends Level
{

    public static float XEND = 4*SuperMarioSurfaceView.WIDTH;

    public Level1(Player player)
    {
        // create the level here
        super(player);

        for (int j = 0; j < 4; j++)
        {
            groundList.add(new Ground(j * SuperMarioSurfaceView.WIDTH, 0.9f * SuperMarioSurfaceView.HEIGHT, player));

        }


        // draw first long row of blocks
       for( int i = 0; i < 7; i++)
       {
           blockList.add(new BreakableBlock(8.0f/15*SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT, player));
       }

        // powerup block
        blockList.add(new QuestionBlock(SuperMarioSurfaceView.WIDTH+SuperMarioSurfaceView.BLOCKWIDTH,1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT,1, player));

        // draw first boundary block
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH+4.0f*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH, player));

        // draw second boundary block
        blockList.add(new GroundBlock(2.0f*SuperMarioSurfaceView.WIDTH+3.0f*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH, player));

        // ending blocks before flag
        for (int j = 1; j <= 3; j++)
        {
            blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH, player));
        }

        for (int j = 2; j <= 3; j++)
        {
            blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-2.0f*SuperMarioSurfaceView.BLOCKWIDTH, player));
        }

        blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + 3*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-3.0f*SuperMarioSurfaceView.BLOCKWIDTH, player));


        // draw flag

        flag.setX(SuperMarioSurfaceView.WIDTH * 3.0f + (2f / 5.0f) * SuperMarioSurfaceView.WIDTH);
        flag.setY(SuperMarioSurfaceView.GROUNDHEIGHT);

    }
}
