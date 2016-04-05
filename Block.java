import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Block Class
 * This is the class used for each block in the field.
 * Each block has a number associated with itself that means the number of
 * bombs around and position i and j representing it's position in the field.
 * Each block can also be a bomb or be turned to show it's number.
 *
 * The Block class is responsible to check if the user is clicking in this
 * current block and handling the play from the user accordingly.
 */
public class Block extends Actor
{
    private Minesweeper game;
    private GreenfootImage blockImage;
    private GreenfootImage bombImage;
    private boolean bomb;
    private int number;
    private int i, j;

    /**
    * Create a new Block object using i and j as it's position.
    * Initialize variables with the default params.
    */
    public Block(int i, int j) {
        blockImage = new GreenfootImage("Block.png");
        bombImage = new GreenfootImage("Bomb.png");

        bomb = false;
        number = 0;

        this.i = i;
        this.j = j;
        setImage(blockImage);
    }

    /**
     * Act - do whatever the test wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }

    /**
    * Get the world instance for this Block as soon as
    * it is added to the world.
    */
    @Override
    protected void addedToWorld(World game) {
        this.game = (Minesweeper) game;
    }

    public void setAsBomb() {
        this.bomb = true;
    }

    public void removeBomb() {
        this.bomb = false;
    }

    public boolean isBomb() {
        return this.bomb;
    }

    public void setBombImage() {
        setImage(bombImage);
    }
}
