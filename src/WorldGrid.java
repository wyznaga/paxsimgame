public class WorldGrid {
    int ht; // grid height
    int wd; // grid width

    public WorldGrid(int height, int width)
    {
        this.ht = height;
        this.wd = width;
        Organism[][] createdGrid = new Organism[this.ht][this.wd];
    }
}

