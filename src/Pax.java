import java.util.Random;

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
    int gestationPax;

    public Pax (Cell[][] gridOn, int inGest)
    {
        this.myGrid = gridOn;
        this.gestationPax = inGest;
    }

    public int getGestation()
    {
        return this.gestationPax;
    }
    public void setGestation(int inGest)
    {
        this.gestationPax = inGest;
    }

    public void simulate()
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
        
        Random randNum = new Random();
        int randomInt = randNum.nextInt(4);
        switch (randomInt) {
            case 0:
                if (myGrid[nIndex][nIndex2].currentState == State.EMPTY) {
                    this.currentMovement = Movement.NORTH;
                    this.currentState = State.MOVE;
                    this.location.x = north.x;
                    this.location.y = north.y;
                    if ((lifetime >= this.gestationPax) && (lifetime % this.gestationPax == 0)) { // need to change these numbers
                        this.willReproduce = true;
                    }
                    this.lifetime++;
                }
                else {
                    this.currentMovement = State.STATIC;
                    this.currentState = Movement.LIVE;
                    this.lifetime++;
                }
                break;
            case 1:
                if (myGrid[eIndex][eIndex2].currentState == State.EMPTY) {
                    this.currentMovement = Movement.EAST;
                    this.currentState = State.MOVE;
                    this.location.x = east.x;
                    this.location.y = east.y;
                    if ((lifetime >= this.gestationPax) && (lifetime % this.gestationPax == 0)) {
                        this.willReproduce = true;
                    }
                    this.lifetime++;
                }
                else {
                    this.currentMovement = State.STATIC;
                    this.currentState = Movement.LIVE;
                    this.lifetime++;
                }
                break;
            case 2:
                if (myGrid[sIndex][sIndex2].currentState == State.EMPTY) {
                    this.currentMovement = Movement.SOUTH;
                    this.currentState = State.MOVE;
                    this.location.x = south.x;
                    this.location.y = south.y;
                    if ((lifetime >= this.gestationPax) && (lifetime % this.gestationPax == 0)) {
                        this.willReproduce = true;
                    }
                    this.lifetime++;
                }
                else {
                    this.currentMovement = Movement.STATIC;
                    this.currentState = State.LIVE;
                    this.lifetime++;
                }
                break;
            case 3:
                if (myGrid[wIndex][wIndex2].currentState == State.EMPTY) {
                    this.currentMovement = Movement.WEST;
                    this.currentState = State.MOVE;
                    this.location.x = west.x;
                    this.location.y = west.y;
                    if ((lifetime >= this.gestationPax) && (lifetime % this.gestationPax == 0)) {
                        this.willReproduce = true;
                    }
                    this.lifetime++;
                }
                else {
                    this.currentMovement = Movement.STATIC;
                    this.currentState = State.LIVE;
                    this.lifetime++;
                }
                break;
        }
    }
}
