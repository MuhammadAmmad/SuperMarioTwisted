package supermariobros.team42.com.supermariobros;

/**
 * Created by twolf on 5/21/15.
 */
public class Level2 extends Level {

    public Level2()
    {
        super();

        // initialize ground
        for (int j = 0; j < 4; j++)
        {
            groundList.add(new Ground(j * SuperMarioSurfaceView.WIDTH, 0.9f * SuperMarioSurfaceView.HEIGHT));

        }

        // breakable blocks
        for( int i = 0; i < 6; i++)
        {
            blockList.add(new BreakableBlock(12.0f/15*SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }
        for( int i = 0; i < 7; i++)
        {
            blockList.add(new BreakableBlock(5.0f/15*SuperMarioSurfaceView.WIDTH + SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }
        for( int i = 0; i < 6; i++)
        {
            blockList.add(new BreakableBlock(2*SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }
        for( int i = 0; i < 5; i++)
        {
            blockList.add(new BreakableBlock(2*SuperMarioSurfaceView.WIDTH + 7.0f/15*SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }

        //Ground blocks
        for (int i = 0; i < 2; i++)
        {
            blockList.add(new GroundBlock(9.0f/15*SuperMarioSurfaceView.WIDTH + SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }

        blockList.add(new GroundBlock(11.0f/15*SuperMarioSurfaceView.WIDTH + SuperMarioSurfaceView.WIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new GroundBlock(2*SuperMarioSurfaceView.WIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new GroundBlock(3*SuperMarioSurfaceView.WIDTH - 1.0f/15*SuperMarioSurfaceView.WIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));

        // ending blocks before flag
        for (int j = 6; j <= 8; j++)
        {
            blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));
        }

        for (int j = 7; j <= 8; j++)
        {
            blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-2.0f*SuperMarioSurfaceView.BLOCKWIDTH));
        }

        blockList.add(new GroundBlock(3.0f*SuperMarioSurfaceView.WIDTH + 8*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-3.0f*SuperMarioSurfaceView.BLOCKWIDTH));

        //question blocks
        blockList.add(new QuestionBlock(SuperMarioSurfaceView.WIDTH + 11*SuperMarioSurfaceView.BLOCKWIDTH, 1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT,2));
        blockList.add(new QuestionBlock(3*SuperMarioSurfaceView.WIDTH - SuperMarioSurfaceView.BLOCKWIDTH, 1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT,2));



        //Enemies
        enemyList.add(new Enemy(1,SuperMarioSurfaceView.WIDTH+13.0f*SuperMarioSurfaceView.BLOCKWIDTH,SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));
        for (int i = 0; i < 3; i++)
        {
            enemyList.add(new Enemy(1,2*SuperMarioSurfaceView.WIDTH+4.0f*i*SuperMarioSurfaceView.BLOCKWIDTH,SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));
        }
        enemyList.add(new Enemy(2,3*SuperMarioSurfaceView.WIDTH+4.0f*SuperMarioSurfaceView.BLOCKWIDTH,SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));


        // draw flag

        flag.setX(SuperMarioSurfaceView.WIDTH * 3.0f + (4f / 5.0f) * SuperMarioSurfaceView.WIDTH);
        flag.setY(SuperMarioSurfaceView.GROUNDHEIGHT);

    }


}