import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Minesweeper Class
 * This class is responsible for creating the world, sorting the
 * initial position of the bombs and checking if the user has won the game.
 */
public class Minesweeper extends World {
    public static final int WIDTH = 12;
    public static final int HEIGHT = 15;
    public static final int CELLSIZE = 30;

    private int fieldSize;
    private int bombs;

    public int hits;
    public boolean firstPlay;

    private Block[][] field;
    /**
     * Constructs the world and initialize the game settings WIDTH
     * the default values, as well as create the matrix of Blocks
     * to be used as the field and add it to the screen.
     */
    public Minesweeper() {
        super(WIDTH, HEIGHT, CELLSIZE, false);

        firstPlay = true;
        hits = 0;
        fieldSize = 10;
        bombs = 10;

        //instanciate field and blocks, add blocks to the world
        field = new Block[fieldSize][fieldSize];

        for(int i = 0, k = 1; i < fieldSize; i++, k++) {
            for(int j = 0, l = 4; j < fieldSize; j++, l++) {
                field[i][j] = new Block(i, j);
                addObject(field[i][j], k, l);
            }
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }
}
