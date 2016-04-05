import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;

/**
 * Block Class
 * This is the class used for each block in the field.
 * Each block has a number associated with itself that means the number of
 * bombs around and position i and j representing it's position in the field.
 * Each block can also be a bomb, be turned to show the number, or have a
 * question marker represented by the star image.
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
     * Check to see if the user is clicking this block and sort the bombs in
     * the field in case it's the first play of the game.
     */
    public void act() {
        if(Greenfoot.mouseClicked(this)) {
            int mouse = Greenfoot.getMouseInfo().getButton();
            // 1 is left button
            if(mouse == 1) {
                if(game.isFirstPlay()) {
                    game.sortBombs(i, j);
                    game.updateNumbers();
                    game.endFirstPlay();
                }
            }
        }
    }
    
    /**
     * Increase the number of all adjacent blocks.
     */
    public void increaseAdjacentNumbers() {
        List<Block> neighbours = getNeighbours(1, true, Block.class);
        for(Block b : neighbours) {
            b.increaseNumber();
        }
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

    public int getNumber() {
        return this.number;
    }

    public void increaseNumber() {
        this.number++;
    }

    public boolean isTurned() {
        return this.turned;
    }
}
