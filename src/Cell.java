import java.util.*;
import java.awt.Point;

public class Cell {
    public Point location = new Point(); // initialize location Point at (0,0)
    public Organism org;
    public enum State {
        EMPTY, GHOST, PAX
    }

    public boolean willBePopulated = false;
    public boolean willHavePax = false;
    public boolean willHaveGhost = false;
    public boolean hadPax = false;
    public boolean hadGhost = false;
    public boolean stayPainted = false;
    public int timer;

    State currentState;

    public Cell()
    {
        this.currentState = (State.EMPTY);
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
    public boolean getWillBePopulated()
    {
        return this.willBePopulated;
    }
    public void setWillHavePax(boolean inWillBool)
    {
        this.willHavePax = inWillBool;
    }
    public boolean getWillHavePax()
    {
        return this.willHavePax;
    }
    public void setWillHaveGhost(boolean inWillBool)
    {
        this.willHaveGhost = inWillBool;
    }
    public boolean getWillHaveGhost()
    {
        return this.willHaveGhost;
    }
    public void setHadGhost(boolean inHadBool)
    {
        this.hadGhost = inHadBool;
    }
    public boolean getHadGhost()
    {
        return this.hadGhost;
    }
    public void setHadPax(boolean inHadBool)
    {
        this.hadPax = inHadBool;
    }
    public boolean getHadPax()
    {
        return this.hadPax;
    }
    public boolean getStayPainted()
    {
        return this.stayPainted;
    }
    public void setStayPainted(boolean stayBool)
    {
        this.stayPainted = stayBool;
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
