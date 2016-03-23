import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Option here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Option extends Actor
{
    private int value = -1;
    
    public Option(String text, int size, Color c1, Color c2, int value) {
        this.value = value;
        setImage(new GreenfootImage(text, size, c1, c2));
    }
    
    /**
     * Act - do whatever the Option wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this) && this.value != -1) {
            Minesweeper.difficult = this.value;
            
            if(this.value == 1) {
                Minesweeper.WIDTH = 12;
                Minesweeper.HEIGHT = 15;
                Minesweeper.CELLSIZE = 30;
            } else if(this.value == 2) {
                Minesweeper.WIDTH = 18;
                Minesweeper.HEIGHT = 23;
                Minesweeper.CELLSIZE = 20;
            } else if(this.value == 3) {
                Minesweeper.WIDTH = 28;
                Minesweeper.HEIGHT = 35;
                Minesweeper.CELLSIZE = 15;
            }
            startGame();
        }
        /*
        if(Greenfoot.getKey().equals("1")) {
            Minesweeper.difficult = 1;
            Minesweeper.WIDTH = 12;
            Minesweeper.HEIGHT = 15;
            Minesweeper.CELLSIZE = 30;
            startGame();
        }
        else if(Greenfoot.getKey().equals("2")) {
            Minesweeper.difficult = 2;
            Minesweeper.WIDTH = 18;
            Minesweeper.HEIGHT = 23;
            Minesweeper.CELLSIZE = 20;
            startGame();
        }
        else if(Greenfoot.getKey().equals("3")) {
            Minesweeper.difficult = 3;
            Minesweeper.WIDTH = 28;
            Minesweeper.HEIGHT = 35;
            Minesweeper.CELLSIZE = 15;
            startGame();
        }
        */
    } 
    
    public void startGame() {
        Minesweeper game = new Minesweeper();
        Greenfoot.setWorld(game);
    }
}
