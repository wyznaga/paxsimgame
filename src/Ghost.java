import java.util.Random;

public class Ghost extends Organism {
    String description;                                                                                                        
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
    int energy;
    int gestationGhost;
    boolean fishSeen;
    
    public Pax (Cell[][] gridOn, int inGest, int inStarve) // add getters and setters
    {
        this.myGrid = gridOn;
        this.gestationGhost = inGest;
        this.energy = inStarve;
    }

    public int getGestation()
    {
        return this.gestationGhost;
    }
    public void setGestation(int inGest)
    {
        this.gestationGhost = inGest
    }
    public int getStarvation()
    {
        return this.energy;
    }

    public void simulate()
    {
        if (this.currentState = DIE)
        {
            this.currentMovement = STATIC;
            this.location = (-1,-1);
        }
        
        else
        {
            for (int i = 0; i < 4; i++)
            {
                switch (i) {
                    case 0:
                        this.north.x = this.location.x;
                        this.north.y = ((this.location + 1)%99);
                        break;
                    case 1:
                        this.east.x = ((this.location.x + 1)%99);
                        this.east.y = this.location.y;
                        break;
                    case 2:
                        this.south.x = this.location.x;
                        this.south.y = ((this.location - 1)%99);
                        break;
                    case 3:
                        this.west.x = ((this.location.x - 1)%99);
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
            
            for (huntIndex = 0; huntIndex < 4; huntIndex++)
            {
                if (this.energy == 0)
                {
                    willDie = true;
                }
                switch (huntIndex) {
                    case 0:
                        if (myGrid[nIndex][nIndex2].currentState == PAX)
                        {
                            this.currentMovement = NORTH;
                            this.currentState = MOVE;
                            this.willEat = true;
                            this.location = (north.x, north.y);
                            this.willReproduce = true;
                            this.fishSeen = true;
                            this.energy++;
                        }
                        break;
                    case 1:
                        if (myGrid[nIndex][nIndex2].currentState == PAX)
                        {
                            this.currentMovement = EAST;
                            this.currentState = MOVE;
                            this.willEat = true;
                            this.location = (east.x, east.y);
                            this.willReproduce = true;
                            this.fishSeen = true;
                            this.energy++;
                        }
                        break;
                    case 2:
                        if (myGrid[nIndex][nIndex2].currentState == PAX)
                        {
                            this.currentMovement = SOUTH;
                            this.currentState = MOVE;
                            this.location = (south.x, south.y);
                            this.willEat = true;
                            this.willReproduce = true;
                            this.fishSeen = true;
                            this.energy++;
                        }
                        break;
                    case 3:
                        if (myGrid[nIndex][nIndex2].currentState == PAX)
                        {
                            this.currentMovement = WEST;
                            this.currentState = MOVE;
                            this.location = (west.x, west.y);
                            this.willEat = true;
                            this.willReproduce = true;
                            this.fishSeen = true;
                            this.energy++;
                        }
                        break;
                }
            }
            
            if (!(fishSeen)) {
                Random randNum = new Random();
                int randomInt = randNum.nextInt(4);
                switch (randomInt) {
                    case 0:
                        if (myGrid[nIndex][nIndex2].currentState == EMPTY) {
                            this.currentMovement = NORTH;
                            this.currentState = MOVE;
                            this.location = (north.x, north.y);
                            this.energy--;
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
                            this.energy--;
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
                            this.energy--;
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
                            this.energy--;
                        }
                        else {
                            this.currentMovement = STATIC;
                            this.currentState = LIVE;
                        }
                        break;
                }
            }
        }
    }
}
