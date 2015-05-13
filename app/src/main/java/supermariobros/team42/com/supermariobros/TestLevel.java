package supermariobros.team42.com.supermariobros;

import java.util.ArrayList;

/**
 * Created by Daniel on 5/12/2015.
 */
public class TestLevel
{
    protected ArrayList<Block> blockList;
    protected ArrayList<Enemy> enemyList;
    protected ArrayList<Ground> groundList;


    public TestLevel()
    {
        blockList = new ArrayList<Block>();
        enemyList = new ArrayList<Enemy>();
        groundList = new ArrayList<Ground>();
        blockList.add(new BreakableBlock(SuperMarioSurfaceView.WIDTH / 15.0f + 200.0f, 200.0f));
        blockList.add(new BreakableBlock(2.0f * SuperMarioSurfaceView.WIDTH / 15.0f + 200.0f, 200.0f));
        blockList.add(new BreakableBlock(3.0f * SuperMarioSurfaceView.WIDTH / 15.0f + 200.0f, 200.0f));
    }


}
