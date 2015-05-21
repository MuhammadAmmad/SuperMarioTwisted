package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/12/2015.
 */
public class Level1 extends Level
{

    public static float XEND = 4*SuperMarioSurfaceView.WIDTH;

    public Level1()
    {
        // create the level here
        super();

        for (int j = 0; j < 4; j++)
        {
            groundList.add(new Ground(j * SuperMarioSurfaceView.WIDTH, 0.9f * SuperMarioSurfaceView.HEIGHT));

        }

        // add enemies

       enemyList.add(new Enemy(1,SuperMarioSurfaceView.WIDTH+5.0f*SuperMarioSurfaceView.BLOCKWIDTH,SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));
        //enemyList.add(new Enemy(1, 200, 200));
        // draw first long row of blocks
       for( int i = 0; i < 7; i++)
       {
           blockList.add(new BreakableBlock(8.0f/15*SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
       }

        // powerup block
        blockList.add(new QuestionBlock(SuperMarioSurfaceView.WIDTH+SuperMarioSurfaceView.BLOCKWIDTH,1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT,1));

        // draw first boundary block
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH+4.0f*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));

        // draw second boundary block
        blockList.add(new GroundBlock(2.0f*SuperMarioSurfaceView.WIDTH+3.0f*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));

        // ending blocks before flag
        for (int j = 1; j <= 3; j++)
        {
            blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));
        }

        for (int j = 2; j <= 3; j++)
        {
            blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-2.0f*SuperMarioSurfaceView.BLOCKWIDTH));
        }

        blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + 3*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-3.0f*SuperMarioSurfaceView.BLOCKWIDTH));


        // draw flag

        flag.setX(SuperMarioSurfaceView.WIDTH * 3.0f + (2f / 5.0f) * SuperMarioSurfaceView.WIDTH);
        flag.setY(SuperMarioSurfaceView.GROUNDHEIGHT);

    }
}
