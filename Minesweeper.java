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
    private int questions;

    public int hits;
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
        questions = 0;
        hits = 0;
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

    /**
    * Check wether the user has won the game.
    */
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

    /**
    * Used for showing the bombs location in case the user has won the game.
    */
    public void showBombs() {
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                if(field[i][j].isBomb()) {
                    field[i][j].setBombImage();
                }
            }
        }
    }

    /**
    * Count the number of question marks in the field to see if the user
    * has mark enough bombs to win the game.
    */
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
    * Update the numbers in each Block based on the number of bombs around it.
    */
    public void updateNumbers() {
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                field[i][j].updateNumber();
            }
        }
    }

    /**
    * Sort the location for the bombs in the field after the first play
    * from the user.
    */
    public void sortBombs(int i, int j) {
        int v1[] = new int[bombs];
        int v2[] = new int[bombs];

        sortBombLocations(v1, v2);
        if(field[i][j].isBomb()) {
            field[i][j].removeBomb();
            sortOneBomb();
        }
        
        /*
        System.out.println("\nv1: ");
        for(int k = 0; k < bombs; k++) {
            System.out.print(String.valueOf(v1[k]) + " ");
        }
        
        System.out.println("\nv2: ");
        for(int k = 0; k < bombs; k++) {
            System.out.print(String.valueOf(v2[k]) + " ");
        }
        
        System.out.println("\n");
        for(int k = 0; k < fieldSize; k++) {
            for(int l = 0; l < fieldSize; l++) {
                if(field[k][l].isBomb()) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        */
    }

    /**
    * Sort the location for the bombs two int[] and scrambling them to
    * be used as positions i and j in the field matrix.
    */
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

    /**
    * Sort the location for one single bomb in case them
    * {@link sortBombLocations(int v1[], int v2[]) sortBombLocations} method
    * has sorted the position in the field where the user has done it's
    * first play.
    */
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
