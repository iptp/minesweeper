import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minesweeper extends World
{
    public static int WIDTH = 12;
    public static int HEIGHT = 15;
    public static int CELLSIZE = 30;
    public static int difficult = 1;
    
    private int fieldSize;
    private int bombs;
    private int questions;
    
    public int hits;
    public boolean firstPlay;
    
    private Block[][] field;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Minesweeper()
    {  
        super(WIDTH, HEIGHT, CELLSIZE, false);
        
        firstPlay = true;
        questions = 0;
        hits = 0;
        switch (difficult) {
            case 1:
                fieldSize = 10;
                bombs = 10;
                break;
            case 2:
                fieldSize = 16;
                bombs = 40;
                break;
            case 3:
                fieldSize = 26;
                bombs = 240;
                break;
            default:
            break;
        }
        
        //instanciate field and blocks, add blocks to the world
        field = new Block[fieldSize][fieldSize];
        
        for(int i = 0, k = 1; i < fieldSize; i++, k++) {
            for(int j = 0, l = 4; j < fieldSize; j++, l++) {
                field[i][j] = new Block(i, j);
                addObject(field[i][j], k, l);
            }
        }
    }
    
    public void checkWin() {
        questions = countQuestions();
        if(questions == bombs && hits == bombs) {
            showText("Ganhou", WIDTH/2, 2);
            showBombs();
            Greenfoot.stop();
        }
        
        int unturned = countUnturned();
        if(unturned == bombs) {
            showText("Ganhou", WIDTH/2, 2);
            showBombs();
            Greenfoot.stop();
        }
    }
    
    public void showBombs() {
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                if(field[i][j].isBomb()) {
                    field[i][j].setBombImage();
                }
            }
        }
    }
    
    public int countQuestions() {
        int count = 0;
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                if(field[i][j].isQuestion()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public int countUnturned() {
        int count = 0;
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                if(!field[i][j].isTurned()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public void updateNumbers() {
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                field[i][j].updateNumber();
            }
        }
    }
    
    public void sortBombs(int i, int j) {
        int v1[] = new int[bombs];
        int v2[] = new int[bombs];
        
        sortBombLocations(v1, v2);
        if(field[i][j].isBomb()) {
            field[i][j].removeBomb();
            sortOneBomb();
        }
    }
    
    public void sortBombLocations(int v1[], int v2[]) {
        for(int i = 0, j = 0; i < bombs; i++, j++) {
            if(j == fieldSize) j = 0;
            v1[i] = j;
        }
        
        for(int i = 0; i < bombs; i++) {
            int r = Greenfoot.getRandomNumber(bombs);
            int aux = v1[i];
            v1[i] = v1[r];
            v1[r] = aux; 
        }
        
        for(int i = 0, j = 0; i < bombs; i++, j++) {
            if(j == fieldSize) j = 0;
            v2[i] = j;
        }
        
        for(int i = 0; i < bombs; i++) {
            int r = Greenfoot.getRandomNumber(bombs);
            int aux = v2[i];
            v2[i] = v2[r];
            v2[r] = aux; 
        }
        
        for(int i = 0; i < bombs; i++) {
            field[v1[i]][v2[i]].setAsBomb();
        }
    }
    
    public void sortOneBomb() {
        int r1, r2;
        do {
            r1 = Greenfoot.getRandomNumber(fieldSize);
            r2 = Greenfoot.getRandomNumber(fieldSize);
        } while(!field[r1][r2].isBomb());
        
        field[r1][r2].setAsBomb();
    }
    
    public int getFieldSize() {
        return fieldSize;
    }
}
