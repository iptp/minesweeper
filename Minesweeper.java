import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Minesweeper Class
 * This class is responsible for creating the world, sorting the
 * initial position of the bombs and checking if the user has won the game.
 */
public class Minesweeper extends World {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int CELLSIZE = 30;

    private int fieldSize;
    private int bombs;
    public boolean firstPlay;

    private Block[][] field;
    /**
     * Constructs the world and initialize the game settings with
     * the default values, as well as create the matrix of Blocks
     * to be used as the field and add it to the screen.
     */
    public Minesweeper() {
        super(WIDTH, HEIGHT, CELLSIZE, false);

        firstPlay = true;
        fieldSize = 10;
        bombs = 10;

        //instanciate field and blocks, add blocks to the world
        field = new Block[fieldSize][fieldSize];

        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                field[i][j] = new Block(i, j);
                addObject(field[i][j], i, j);
            }
        }
    }

    /*
     * Iterates over the field looking for bombs and increasing it's adjacent
     * numbers whenever we find one bomb.
     */
    public void updateNumbers() {
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                if(field[i][j].isBomb()) {
                    field[i][j].increaseAdjacentNumbers();
                }
            }
        }
        printField();
    }

    /**
    * Check wether the user has won the game.
    */
    public void checkWin() {
        int unturned = countUnturned();
        if(unturned == bombs) {
            showText("You won!", WIDTH/2, 2);
            Greenfoot.stop();
        }
    }

    /**
    * Count the number of field Blocks that are unturned to see if the user
    * has won the game.
    */
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

    /**
    * Sort the location for the bombs two int[] and scrambling them to
    * be used as positions i and j in the field matrix.
    */
    public void sortBombs(int i, int j) {
        //create and fill the 2 vectors
        int v1[] = new int[bombs];
        int v2[] = new int[bombs];
        for(int k = 0; k < bombs; k++) {
            v1[k] = k;
            v2[k] = k;
        }

        //scramble them using random values for the positions
        for(int k = 0; k < bombs; k++) {
            int r = Greenfoot.getRandomNumber(bombs);
            int aux = v1[r];
            v1[r] = v1[k];
            v1[k] = aux;

            r = Greenfoot.getRandomNumber(bombs);
            aux = v2[r];
            v2[r] = v2[k];
            v2[k] = aux;
        }

        //put bombs in their positions
        for(int k = 0; k < bombs; k++) {
            int x = v1[k];
            int y = v2[k];
            field[x][y].setAsBomb();
        }
        checkAndRemoveBomb(i, j);
        printField();
    }

    public void checkAndRemoveBomb(int i, int j) {
        if(field[i][j].isBomb()) {
            field[i][j].removeBomb();
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public boolean isFirstPlay() {
        return firstPlay;
    }

    public void endFirstPlay() {
        firstPlay = false;
    }

    /*
     * Used to print the field in the console whenever we need to test the game.
     */
    public void printField() {
        System.out.println("\n");
        for(int k = 0; k < fieldSize; k++) {
            for(int l = 0; l < fieldSize; l++) {
                if(field[k][l].isBomb()) {
                    System.out.print("X ");
                } else {
                    System.out.print(field[k][l].getNumber() + " ");
                }
            }
            System.out.println();
        }
    }
}
