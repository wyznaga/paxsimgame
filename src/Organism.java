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
        MOVE, LIVE
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
    
    // add the rest of getters and setters here...s
}
