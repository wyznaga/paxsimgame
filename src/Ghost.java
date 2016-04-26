public class Ghost extends Organism {
    String description;                                                                                                        
    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String desc)
    {
        this.description = desc;
    }

    public void simulate()
    {
        Cell[] adjacents = new Cell[4]; // 4 cardinally-adjacent Cells
        ;
    }
}
