import java.util.*;
import java.awt.Point;

public class Cell {
    public Point location = new Point(); // initialize location Point at (0,0)
    public Organism org;
    public enum State {
        EMPTY, GHOST, PAX, PILL
    }

    State currentState;

    public void setLocation(int xLoc, int yLoc)
    {
        this.location.x = xLoc;
        this.location.y = yLoc;
    }

    public Point getLocation()
    {
        return new Point(this.location);
    }
}
