import java.util.*;
import java.awt.Point;

public class Cell {
    public Point location = new Point(); // initialize location Point at (0,0)
    public Organism org;
    public enum State {
        EMPTY, GHOST, PAX
    }

    public boolean willBePopulated;
    public int timer;

    State currentState;

    public Cell()
    {
        this.currentState = EMPTY;
    }

    public void setLocation(int xLoc, int yLoc)
    {
        this.location.x = xLoc;
        this.location.y = yLoc;
    }
    public Point getLocation()
    {
        return new Point(this.location);
    }
    public void setState(State inState)
    {
        this.currentState = inState;
    }
    public State getState()
    {
        return this.currentState;
    }
    public void setWillBePopulated(boolean inWillBool)
    {
        this.willBePopulated = inWillBool;
    }
    public bool getWillBePopulated()
    {
        return this.willBePopulated;
    }
    public void setTimer(int timerIn)
    {
        this.timer = timerIn;
    }
    public int getTimer()
    {
        return this.timer;
    }
}
