import java.awt.Point;

public class Organism {
    public String name;
    public Point location = new Point(); // construct new location Point at default of (0,0)
    public enum State {
        MOVE, LIVE, REPRODUCE, TIRE, EAT
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
}

