import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;

/**
 * Block Class
 * This is the class used for each block in the field.
 * Each block has a number associated with itself that means the number of
 * bombs around and position i and j representing it's position in the field.
 * Each block can also be a bomb or be flipped to show it's number.
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
    private boolean flipped;
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
        flipped = false;
        number = 0;

        this.i = i;
        this.j = j;
        setImage(blockImage);
    }

    /**
     * Check to see if the user is clicking this block and handle the
     * click by calling the world's methods for setting the bombs after
     * the first play, updating the numbers, fliping the block
     * and checking if the user won the game.
     */
    public void act() {
        if(Greenfoot.mouseClicked(this)) {
            int mouse = Greenfoot.getMouseInfo().getButton();
            // 1 is left button
            if(mouse == 1) {
                if(game.isFirstPlay()) {
                    game.setBombs(i, j);
                    game.updateNumbers();
                    game.endFirstPlay();
                }
                flip();
            }
        }
    }

    /**
    * Flip this block after checking if it's not a bomb.
    * If the block number equals 0, recursively flips all adjacent blocks
    * until any block number different than 0 is found.
    */
    public void flip() {
        if(this.bomb) {
            setImage(bombImage);
            game.showText("Game over!", Minesweeper.WIDTH/2, 2);
            Greenfoot.stop();
        }
        else {
            //Display number
            String n = String.valueOf(this.number);
            Color bg = new Color(0, 0, 0, 0); //transparent
            GreenfootImage numberImg = new GreenfootImage(n, game.CELLSIZE, Color.BLACK, bg);
            setImage(numberImg);

            this.flipped = true;

            if(this.number == 0){
                //flip adjacent blocks
                List<Block> neighbours = getNeighbours(1, true, Block.class);
                for(Block b : neighbours) {
                    if(!b.isFlipped()) {
                        b.flip();
                    }
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

    public boolean isFlipped() {
        return this.flipped;
    }
}
