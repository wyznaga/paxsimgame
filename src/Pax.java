import java.util.*;

public class Pax extends Organism {
    String description;
    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String desc)
    {
        this.description = desc;
    }

    Cell[][] myGrid;
    
    int nIndex;
    int nIndex2;
    int eIndex;
    int eIndex2;
    int sIndex;
    int sIndex2;
    int wIndex;
    int wIndex2;

    public Pax (Cell[][] gridOn)
    {
        this.myGrid = gridOn;
    }

    public void simulate()
    {
        for (int i = 0; i < 4; i++)
        {
            switch (i) {
                case 0:
                    this.north.x = this.location.x;
                    this.north.y = ((this.location + 1)%100);
                    break;
                case 1:
                    this.east.x = ((this.location.x + 1)%100);
                    this.east.y = this.location.y;
                    break;
                case 2:
                    this.south.x = this.location.x;
                    this.south.y = ((this.location - 1)%100);
                    break;
                case 3:
                    this.west.x = ((this.location.x - 1)%100);
                    this.west.y = this.location.y;
                    break;
            }
        }
        
        for (ns = 0; ns < 100; ns++) {
            for (ns2 = 0; ns2 < 100; ns2++) {
                if (myGrid[ns][ns2].location.equals(north))
                {
                    nIndex = ns;
                    nIndex2 = ns2;
                }
            }
        }
        for (es = 0; ns < 100; ns++) {
            for (es2 = 0; es2 < 100; es2++) {
                if (myGrid[es][es2].location.equals(east))
                {
                    eIndex = es;
                    eIndex2 = es2;
                }
            }
        }
        for (ss = 0; ss < 100; ss++) {
            for (ss2 = 0; ss2 < 100; ss2++) {
                if (myGrid[ss][ss2].location.equals(south))
                {
                    sIndex = ss;
                    sIndex2 = ss2;
                }
            }
        }
        for (ws = 0; ws < 100; ws++) {
            for (ws2 = 0; ws2 < 100; ws2++) {
                if (myGrid[ws][ws2].location.equals(west))
                {
                    wIndex = ws;
                    wIndex2 = ws2;
                }
            }
        }
        
        Random randNum = new Random();
        int randomInt = randNum.nextInt(4);
        switch (randomInt) {
            case 0:
                if (myGrid[nIndex][nIndex2].currentState == EMPTY) {
                    this.currentMovement = NORTH;
                    this.currentState = MOVE;
                    this.location = (north.x, north.y);
                    if ((lifetime >= 5) && (lifetime % 5 == 0)) {
                        this.willReproduce = true;
                    }
                }
                else {
                    this.currentMovement = STATIC;
                    this.currentState = LIVE;
                }
                break;
            case 1:
                if (myGrid[eIndex][eIndex2].currentState == EMPTY) {
                    this.currentMovement = EAST;
                    this.currentState = MOVE;
                    this.location = (east.x, east.y);
                    if ((lifetime >= 5) && (lifetime % 5 == 0)) {
                        this.willReproduce = true;
                    }
                }
                else {
                    this.currentMovement = STATIC;
                    this.currentState = LIVE;
                }
                break;
            case 2:
                if (myGrid[sIndex][sIndex2].currentState == EMPTY) {
                    this.currentMovement = SOUTH;
                    this.currentState = MOVE;
                    this.location = (south.x, south.y);
                    if ((lifetime >= 5) && (lifetime % 5 == 0)) {
                        this.willReproduce = true;
                    }
                }
                else {
                    this.currentMovement = STATIC;
                    this.currentState = LIVE;
                }
                break;
            case 3:
                if (myGrid[wIndex][wIndex2].currentState == EMPTY) {
                    this.currentMovement = WEST;
                    this.currentState = MOVE;
                    this.location = (west.x, west.y);
                    if ((lifetime >= 5) && (lifetime % 5 == 0)) {
                        this.willReproduce = true;
                    }
                }
                else {
                    this.currentMovement = STATIC;
                    this.currentState = LIVE;
                }
                break;
        }
    }
}
