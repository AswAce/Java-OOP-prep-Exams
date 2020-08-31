package models.fish;

public class SaltwaterFish extends BaseFish {

    private static final int GROW_SIZE = 2;
    private static final int INITIAL_SIZE = 5;
//    Can only live in SaltwaterAquarium!


    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + GROW_SIZE);
    }
}
