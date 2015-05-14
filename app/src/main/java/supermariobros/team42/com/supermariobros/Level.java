package supermariobros.team42.com.supermariobros;

import java.util.ArrayList;

/**
 * Created by Daniel on 5/11/2015.
 */
public abstract class Level
{
    protected ArrayList<Block> blockList;
    protected ArrayList<Enemy> enemyList;
    protected ArrayList<Ground> groundList;
    protected Flag flag;


    public Level()
    {
        blockList = new ArrayList<Block>();
        enemyList = new ArrayList<Enemy>();
        groundList = new ArrayList<Ground>();
        flag = new Flag();
    }



}
