package supermariobros.team42.com.supermariobros;

/**
 * Created by Daniel on 5/12/2015.
 */
public class Level1 extends Level
{


    public Level1()
    {
        // create the level here

        for (int j = 0; j < 4; j++)
        {
            groundList.add(new Ground(j * SuperMarioSurfaceView.WIDTH, 0.9f * SuperMarioSurfaceView.HEIGHT));

        }


        // draw first long row of blocks
        for (int j = 0; j < 10; j++)
        {
            if (j != 7)
            {
                blockList.add(new BreakableBlock(3.0f * SuperMarioSurfaceView.WIDTH / 5.0f + j * SuperMarioSurfaceView.WIDTH / 15.0f, (2.0f / 3.0f) * SuperMarioSurfaceView.BLOCKWIDTH));
            }
            else
            {
                blockList.add(new PowerUpBlock(SuperMarioSurfaceView.WIDTH / 5.0f + j * SuperMarioSurfaceView.WIDTH / 15.0f, (2.0f / 3.0f) * SuperMarioSurfaceView.BLOCKWIDTH, 1));

            }
        }

        for (int j = 0; j < 9; j++)
        {
            blockList.add(new BreakableBlock(SuperMarioSurfaceView.WIDTH + (2.0f / 5.0f) * SuperMarioSurfaceView.WIDTH, SuperMarioSurfaceView.BLOCKWIDTH / 3.0f));
        }

        // draw powerup block

        blockList.add(new PowerUpBlock(SuperMarioSurfaceView.WIDTH * 2.0f + 2.0f * SuperMarioSurfaceView.BLOCKWIDTH
                , 0.0f, 1));

        // bounding block
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH * 2.0f, SuperMarioSurfaceView.BLOCKWIDTH - SuperMarioSurfaceView.BLOCKWIDTH));

        // blocks before flag
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH * 3.0f + SuperMarioSurfaceView.WIDTH * (2.0f / 5.0f), SuperMarioSurfaceView.BLOCKWIDTH - SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH * 3.0f + SuperMarioSurfaceView.WIDTH * (2.0f / 5.0f) + SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.BLOCKWIDTH - SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH * 3.0f + SuperMarioSurfaceView.WIDTH * (2.0f / 5.0f) + 2.0f * SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.BLOCKWIDTH - SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH * 3.0f + SuperMarioSurfaceView.WIDTH * (2.0f / 5.0f), SuperMarioSurfaceView.BLOCKWIDTH - 2.0f * SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH * 3.0f + SuperMarioSurfaceView.WIDTH * (2.0f / 5.0f) + SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.BLOCKWIDTH - 2.0f * SuperMarioSurfaceView.BLOCKWIDTH));

        flag.setX(SuperMarioSurfaceView.WIDTH * 3.0f + (4.0f / 5.0f) * SuperMarioSurfaceView.WIDTH);
        flag.setY(SuperMarioSurfaceView.BLOCKWIDTH);

    }
}
