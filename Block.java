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
    private GreenfootImage starImage;
    private boolean bomb;
    private boolean turned;
    private boolean question;
    private int number;
    private int i, j;

    /**
    * Create a new Block object using i and j as it's position.
    * Initialize variables with the default params.
    */
    public Block(int i, int j) {
        blockImage = new GreenfootImage("Block.png");
        bombImage = new GreenfootImage("Bomb.png");
        starImage = new GreenfootImage("Star.png");

        bomb = false;
        turned = false;
        question = false;
        number = 0;

        this.i = i;
        this.j = j;
        setImage(blockImage);
    }

    /**
    * Get the world instance for this Block as soon as
    * it is added to the world.
    */
    @Override
    protected void addedToWorld(World game) {
        this.game = (Minesweeper) game;
    }

    /**
     * Check to see if the user is clicking this block and handle the
     * click by calling the world's methods for sorting the bombs after
     * the first play, updating the numbers, turning the block, displaying
     * the question marker and checking if the user won the game.
     */
    public void act() {
        if(Greenfoot.mouseClicked(this)) {
            int mouse = Greenfoot.getMouseInfo().getButton();
            if(mouse == 1 && !question) {
                if(game.firstPlay) {
                    game.sortBombs(i, j);
                    game.updateNumbers();
                    game.firstPlay = false;
                }
                turn();
            }
            else if(mouse == 3) {
                putQuestion();
            }
            game.checkWin();
        }
    }

    /**
    * Display the question marker in this blocks.
    */
    public void putQuestion() {
        if(!turned && !question) {
            setImage(starImage);
            this.question = true;
            if(bomb) {
                game.hits++;
            }
        } else if(!turned && question) {
            setImage(blockImage);
            this.question = false;
            if(bomb) {
                game.hits--;
            }
        } else if(turned && question) {
            setImage(new GreenfootImage(String.valueOf(this.number),
                game.CELLSIZE, Color.BLACK, new Color(0, 0, 0, 0)));
        }
    }

    /**
    * Turn this block after checking if it's not a bomb.
    */
    public void turn() {
        if(bomb) {
            //explode
            setImage(bombImage);
            game.showText("Perdeu", Minesweeper.WIDTH/2, 2);
            Greenfoot.stop();
        } else {
            this.turned = true;
            if(!question) {
                GreenfootImage img = new GreenfootImage(String.valueOf(this.number),
                game.CELLSIZE, Color.BLACK, new Color(0, 0, 0, 0));
                setImage(img);
            }
            if(number == 0) {
                List<Block> neighbours = getNeighbours(1, true, Block.class);
                for(Block b : neighbours) {
                    if(!b.isTurned()) {
                        b.turn();
                    }
                }
            }
        }
    }

    /**
    * Update the number of this block by counting the amount of bombs arount it.
    */
    public void updateNumber() {
        List<Block> neighbours = getNeighbours(1, true, Block.class);
        int cont = 0;
        for(Block b : neighbours) {
            if(b.bomb) {
                cont++;
            }
        }
        number = cont;
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

    public boolean isTurned() {
        return this.turned;
    }

    public boolean isQuestion() {
        return this.question;
    }

    public void setBombImage() {
        setImage(bombImage);
    }
}
