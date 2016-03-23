import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        super(40, 40, 10, false);
        background(Color.BLACK);
        addObject(new Option("Minesweeper", 36, Color.WHITE, Color.BLACK, -1), 20, 5);
        addObject(new Option("Selecione a dificuldade:", 26, Color.WHITE, Color.BLACK, -1), 20, 12);
        
        addObject(new Option("Fácil", 26, Color.WHITE, Color.BLACK, 1), 20, 20);
        addObject(new Option("Médio", 26, Color.WHITE, Color.BLACK, 2), 20, 25);
        addObject(new Option("Difícil", 26, Color.WHITE, Color.BLACK, 3), 20, 30);
        
    }
    
    public void background(Color c) {
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(c);
        bg.fill();
        setBackground(bg);
    }
}
