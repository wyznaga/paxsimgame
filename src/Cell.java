import java.util.*;
import java.awt.Point;

public class Cell {
    public Point location = new Point(); // initialize location Point at (0,0)
    public Organism org;
    public enum State {
        EMPTY, PREDATOR, PREY
    }

    State currentState;
}

