import java.awt.Point;

public class Organism {
    public String name;
    public Point location = new Point(); // construct new location Point at default of (0,0)
    public Point north = new Point();
    public Point east = new Point();
    public Point south = new Point();
    public Point west = new Point();
    int lifetime;
    boolean willReproduce;
    boolean willTire;
    boolean willEat;
    boolean willDie;
    public enum State {
        MOVE, LIVE, DIE
    }
    public enum Movement {
        NORTH, SOUTH, EAST, WEST, STATIC;
    }

    State currentState;
    Movement currentMovement;
    void simulate()
    {
        this.currentState = State.LIVE;
        this.currentMovement = Movement.STATIC;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String nm)
    {
        this.name = nm;
    }
    public int getLifetime()
    {
        return this.lifetime;
    }
    public void setLifetime(int ltime)
    {
        this.lifetime = ltime;
    }
    public Point getNorth()
    {
        return this.north;
    }
    public void setNorth(Point inPoint)
    {
        this.north = inPoint;
    }
    public Point getEast()
    {
        return this.east;
    }
    public void setEast(Point inPoint)
    {
        this.east = inPoint;
    }
    public Point getSouth()
    {
        return this.south;
    }
    public void setSouth(Point inPoint)
    {
        this.south = inPoint;
    }
    public Point getWest()
    {
        return this.west;
    }
    public void setWest(Point inPoint)
    {
        this.west = inPoint;
    }
    public boolean getWillReproduce()
    {
        return this.willReproduce;
    }
    public void setWillReproduce(boolean inBool)
    {
        this.willReproduce = inBool;
    }
    public boolean getWillEat()
    {
        return this.willEat;
    }
    public void setWillEat(boolean inBool)
    {
        this.willEat = inBool;
    }
    public boolean getWillTire()
    {
        return this.willReproduce;
    }
    public void setWillTire(boolean inBool)
    {
        this.willTire = inBool;
    }
    public boolean getWillDie()
    {
        return this.willDie;
    }
    public void setWillDie(boolean inBool)
    {
        this.willDie = inBool;
    }
}
