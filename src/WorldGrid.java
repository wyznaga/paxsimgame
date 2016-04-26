import java.awt.Point;

public class WorldGrid {
    int ht; // grid height
    int wd; // grid width
    

    public WorldGrid(int height, int width)
    {
        this.ht = height;
        this.wd = width;
        Cell[][] createdGrid = new Cell[this.ht][this.wd];
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 2; column++) {
                createdGrid[row][column].location.x = column;
                createdGrid[row][column].location.y = (2 - row);
            }
        }
    }
}
