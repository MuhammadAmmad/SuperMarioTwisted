package supermariobros.team42.com.supermariobros;

/**
 * Created by twolf on 5/21/15.
 */
public class Level3 extends Level {

    public Level3() {
        super();

        // initialize ground
        for (int j = 0; j < 5; j++) {
            groundList.add(new Ground(j * SuperMarioSurfaceView.WIDTH, 0.9f * SuperMarioSurfaceView.HEIGHT));
        }

        //breakable blocks
        for (int i = 0; i<9; i++)
        {
            blockList.add(new BreakableBlock(SuperMarioSurfaceView.WIDTH + 9.0f/15*SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }
        for (int i = 0; i<6; i++)
        {
            blockList.add(new BreakableBlock(2*SuperMarioSurfaceView.WIDTH + 9.0f/15*SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }
        blockList.add(new BreakableBlock(2*SuperMarioSurfaceView.WIDTH + 9.0f/15*SuperMarioSurfaceView.WIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new BreakableBlock(2*SuperMarioSurfaceView.WIDTH + 14.0f/15*SuperMarioSurfaceView.WIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));

        //groundblocks
        blockList.add(new GroundBlock(0,SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        for (int i = 0; i<7; i++)
        {
            blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        }
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH + 8.0f/15*SuperMarioSurfaceView.WIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT));
        blockList.add(new GroundBlock(SuperMarioSurfaceView.WIDTH + 8.0f/15*SuperMarioSurfaceView.WIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        blockList.add(new GroundBlock(2*SuperMarioSurfaceView.WIDTH + 6.0f/15*SuperMarioSurfaceView.WIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));

        //stairs
        for (int i = 0; i<5; i++)
        {
            blockList.add(new GroundBlock(3*SuperMarioSurfaceView.WIDTH + 2*SuperMarioSurfaceView.BLOCKWIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        }
        for (int i = 0; i<4; i++)
        {
            blockList.add(new GroundBlock(3*SuperMarioSurfaceView.WIDTH + 3*SuperMarioSurfaceView.BLOCKWIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - 2*SuperMarioSurfaceView.BLOCKWIDTH));
        }
        for (int i = 0; i<3; i++)
        {
            blockList.add(new GroundBlock(3*SuperMarioSurfaceView.WIDTH + 4*SuperMarioSurfaceView.BLOCKWIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - 3*SuperMarioSurfaceView.BLOCKWIDTH));
        }
        for (int i = 0; i<2; i++)
        {
            blockList.add(new GroundBlock(3*SuperMarioSurfaceView.WIDTH + 5*SuperMarioSurfaceView.BLOCKWIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - 4*SuperMarioSurfaceView.BLOCKWIDTH));
        }
        for (int i = 0; i<1; i++)
        {
            blockList.add(new GroundBlock(3*SuperMarioSurfaceView.WIDTH + 6*SuperMarioSurfaceView.BLOCKWIDTH + i*SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - 5*SuperMarioSurfaceView.BLOCKWIDTH));
        }

        // ending blocks before flag
        for (int j = 6; j <= 8; j++)
        {
            blockList.add(new GroundBlock(4.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-SuperMarioSurfaceView.BLOCKWIDTH));
        }
        for (int j = 7; j <= 8; j++)
        {
            blockList.add(new GroundBlock(4.0f*SuperMarioSurfaceView.WIDTH + j*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-2.0f*SuperMarioSurfaceView.BLOCKWIDTH));
        }
        blockList.add(new GroundBlock(4.0f*SuperMarioSurfaceView.WIDTH + 8*SuperMarioSurfaceView.BLOCKWIDTH , SuperMarioSurfaceView.GROUNDHEIGHT-3.0f*SuperMarioSurfaceView.BLOCKWIDTH));

        //Question blocks
        blockList.add(new QuestionBlock(SuperMarioSurfaceView.WIDTH + 7*SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT,1));
        blockList.add(new QuestionBlock(2*SuperMarioSurfaceView.WIDTH + 11*SuperMarioSurfaceView.BLOCKWIDTH, 1.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT,1));


        //enemies
        for (int i = 0; i<4; i++)
        {
            enemyList.add(new Enemy(1, i * 6 * SuperMarioSurfaceView.BLOCKWIDTH + 13.0f * SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        }
        for (int i = 0; i<4; i++)
        {
            enemyList.add(new Enemy(1, 3*SuperMarioSurfaceView.WIDTH + i * 3 * SuperMarioSurfaceView.BLOCKWIDTH + 9.0f * SuperMarioSurfaceView.BLOCKWIDTH, SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));
        }
        enemyList.add(new Enemy(1, 2*SuperMarioSurfaceView.WIDTH + 12.0f * SuperMarioSurfaceView.BLOCKWIDTH, 3.0f/5*SuperMarioSurfaceView.GROUNDHEIGHT - SuperMarioSurfaceView.BLOCKWIDTH));


        // draw flag

        flag.setX(SuperMarioSurfaceView.WIDTH * 4.0f + (4f / 5.0f) * SuperMarioSurfaceView.WIDTH);
        flag.setY(SuperMarioSurfaceView.GROUNDHEIGHT);
    }
}
