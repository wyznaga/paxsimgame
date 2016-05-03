import java.awt.Point;
import java.util.Random;

public class WorldGrid {
    int ht; // grid height
    int wd; // grid width
    boolean isRunning;
    int timeDelay = 50;

    int countGhostInGrid = 0;
    int gestGhostInGrid = 0;
    int starveGhostInGrid = 0;
    int countPaxInGrid = 0;
    int gestPaxInGrid = 0;

    public WorldGrid(int height, int width)
    {
        this.ht = height;
        this.wd = width;
        Cell[][] createdGrid = new Cell[this.ht][this.wd];
        for (int row = 0; row < ht; row++) {
            for (int column = 0; column < wd; column++) {
                createdGrid[row][column].location.x = column;
                createdGrid[row][column].location.y = (this.ht - row);
            }
        }
    }

    public void setRunning(boolean inBool)
    {
        this.isRunning = inBool;
    }
    public boolean getRunning()
    {
        return this.isRunning;
    }
    public void setDelay(int inInt)
    {
        this.timeDelay = inInt;
    }
    public int getDelay()
    {
        return this.timeDelay;
    }

    public void populate(int countGhostIn, int gestGhostIn, int starveGhostIn, int countPaxIn, int gestPaxIn)
    {
        Pax[] paxArr = new Pax[countPaxIn];
        Ghost[] ghostArr = new Ghost[countGhostIn];
        for (int i = 0; i < countPaxIn; i++)
        {
            while (true)
            {
                Random randomPaxPointX = new Random();
                int paxPointX = randomPaxPointX.nextInt(wd);
                int paxPointY = randomPaxPointX.nextInt(ht);
                Point paxPointPlace = new Point(paxPointX, paxPointY);
                if (createdGrid[((this.wd-1)-paxPointX)][((this.ht-1)-paxPointY)].getState == State.EMPTY)
                {
                    createdGrid[((this.wd-1)-paxPointX)][((this.ht-1)-paxPointY)].setState(PAX);
                    paxArr[i].setLocation(paxPointPlace);
                    this.gestPaxInGrid = gestPaxIn;
                    paxArr[i].setGestation(this.gestPaxInGrid);
                    createdGrid[((this.wd-1)-paxPointX)][((this.ht-1)-paxPointY)].setWillHavePax(true);
                    break;
                }
                else if (!(createdGrid[((this.wd-1)-paxPointX)][((this.ht-1)-paxPointY)].getState == State.EMPTY))
                {
                    continue;
                }
            }
        }
        for (int j = 0; j < countGhostIn; j++)
        {
            while (true)
            {
                Random randomGhostPointX = new Random();
                int ghostPointX = randomGhostPointX.nextInt(wd);
                int ghostPointY = randomGhostPointX.nextInt(ht);
                Point ghostPointPlace = new Point(ghostPointX, ghostPointY);
                if (createdGrid[((this.wd-1)-ghostPointX)][((this.ht-1)-ghostPointY)].getState == State.EMPTY)
                {
                    createdGrid[((this.wd-1)-ghostPointX)][((this.ht-1)-ghostPointY)].setState(PAX);
                    ghostArr[j].setLocation(ghostPointPlace);
                    this.gestGhostInGrid = gestGhostIn;
                    this.starveGhostInGrid = starveGhostIn;
                    ghostarr[j].setGestation(this.gestGhostInGrid);
                    ghostArr[j].setStarvation(this.starveGhostInGrid);
                    createdGrid[((this.wd-1)-ghostPointX)][((this.ht-1)-ghostPointY)].setWillHaveGhost(true);
                    break;
                }
                else if (!(createdGrid[((this.wd-1)-ghostPointX)][((this.ht-1)-ghostPointY)].getState == State.EMPTY))
                {
                    continue;
                }
            }
        }
    }
    
    public void simulate()
    {
        for (int row = ht; row > 0; row--) {
            for (int column = 0; column < this.wd; column++) {
                // createdGrid[row][column].location.x is column;
                // createdGrid[row][column].location.y is (this.ht - row)
                if (createdGrid[row][column].getState(State.PAX))
                {
                    if (createdGrid[row][column].getWillHavePax() && createdGrid[row][column].getWillHaveGhost())
                    {
                        createdGrid[row][column].setState(State.GHOST);
                        createdGrid[row][column].setWillHaveGhost(true);
                        createdGrid[row][column].setHadPax(true);
                    }
                    for (int i = 0; i < this.countPaxInGrid; i++)
                    {
                        paxArr[i].simulate();
                        if(paxArr[i].getWillReproduce)
                        {
                            createdGrid[row][column].setWillBePopulated();
                        }
                    }
                }
                else if (createdGrid[row][column].getState == State.GHOST)
                {
                    for (int j = 0; j < this.countGhostInGrid; j++)
                    {
                        ghostArr[j].simulate();
                        if(ghostArr[j].getWillReproduce)
                        {
                            createdGrid[row][column].setWillBePopulated();
                            createdGrid[row][column].setTimer(this.gestGhostInGrid);
                        }
                    }
                }
                else
                {
                    continue;
                }
            }
        }
    }
}
