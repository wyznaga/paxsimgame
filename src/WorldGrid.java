import java.awt.Point;
impot java.util.Random;

public class WorldGrid {
    int ht; // grid height
    int wd; // grid width
    boolean isRunning;
    int timeDelay = 50;

    public WorldGrid(int height, int width)
    {
        this.ht = height;
        this.wd = width;
        Cell[][] createdGrid = new Cell[this.ht][this.wd];
        for (int row = 0; row < ht; row++) {
            for (int column = 0; column < wd; column++) {
                createdGrid[row][column].location.x = column;
                createdGrid[row][column].location.y = (2 - row);
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
        for (int i = 0; i < countPaxIn; i++)
        {
            Random randomPaxPointX = new Random();
            int paxPointX = randomPaxPointX.nextInt(wd);
            int paxPointY = randomPaxPointX.nextInt(ht);
            Point paxPointPlace = new Point(paxPointX, paxPointY);
            if (createdGrid[((this.wd-1)-paxPointX)][((this.ht-1)-paxPointY)].getState == EMPTY)
            {
                
            }
            else
            {
                
            }
        }
        for (int j = 0; j < countGhostIn; j++)
        {
            
        }
    }
}
