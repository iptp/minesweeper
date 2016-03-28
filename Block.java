import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;

/**
 * Write a description of class Block here.
 * 

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
    
    @Override
    protected void addedToWorld(World game) {
        this.game = (Minesweeper) game;
    }
    
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
