package supermariobros.team42.com.supermariobros;

import java.util.Random;

/**
 * Created by Daniel on 5/11/2015.
 */
public class LevelGenerator
{
    protected Random rand = new Random();
    protected int r = 0;
    protected float currentX = 0;
    protected final float FLOATINGHEIGHT = SuperMarioSurfaceView.HEIGHT / 10.0f;

    public Level createLevel()
    {
        Level newLevel = new Level();
        currentX = 3.0f * SuperMarioSurfaceView.WIDTH / 15.0f;
        // generate level elements such as blocks and items
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                r = rand.nextInt(2) + 1;
                switch (r)
                {

                    case 1:
                        newLevel.blockList.add(new BreakableBlock(currentX, FLOATINGHEIGHT));
                        currentX += SuperMarioSurfaceView.WIDTH / 15.0f;
                        break;
                    case 2:
                        newLevel.blockList.add(new QuestionBlock(currentX, FLOATINGHEIGHT));
                        currentX += SuperMarioSurfaceView.WIDTH / 15.0f;
                        break;


                }
            }
        }
    }
}
