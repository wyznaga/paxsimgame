import java.awt.Point;

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
    
    public void populate(int cGIn, int gGIn, int sGIn, int cPIn, int gPIn)
    {
        
    }
}
