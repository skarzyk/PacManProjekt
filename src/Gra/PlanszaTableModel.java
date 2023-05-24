package Gra;
import java.awt.Point;
import javax.swing.table.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class PlanszaTableModel extends AbstractTableModel implements KeyListener {

    public int score =0;
    private Object[][] data;
    private final int kolumna;
    private final int wiersz;

    public static final Object EMPTY = 2;
    public static final Object WALL = 1;
    public static final Object COIN = 0;
    public static final Object PACMAN = 3;
    public static final Object GHOST =4;
    public final int[][] Map;
    private Point pacmanPosition;

    private PlanszaTableModel plansza;

    Pacman pacman;


    public PlanszaTableModel(int szerokoscPlanszy, int wysokoscPlanszy) {
        data = new Object[szerokoscPlanszy][wysokoscPlanszy];
        this.kolumna = szerokoscPlanszy;
        this.wiersz = wysokoscPlanszy;
        Map = new int[szerokoscPlanszy][wysokoscPlanszy];
        generateLabirynt();
        przejscia();
        sciany();
        tworzeniePacman();
        pacman = new Pacman(pacmanPosition, this);


    }


    @Override
    public int getRowCount() {
        return wiersz;
    }

    @Override
    public int getColumnCount() {
        return kolumna;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    private void generateLabirynt() {

        for (int row = 0; row < wiersz; row++) {
            for (int col = 0; col < kolumna; col++) {
                Map[row][col] = (int) WALL;
            }
        }



        dfs(1, 1);


        for (int row = 0; row < wiersz; row++) {
            for (int col = 0; col < kolumna; col++) {
                if (Map[row][col] == (int) WALL) {
                    data[row][col] = WALL;
                } else {
                    data[row][col] = EMPTY;
                }
            }
        }
    }

    private void dfs(int row, int col) {

        Map[row][col] = (int) EMPTY;


        int[] directions = {-1, 1};
        randomShuffle(directions);

        for (int dir : directions) {
            if (dir == -1 && row > 2 && Map[row - 2][col] == (int) WALL) {
                // Idź w górę
                Map[row - 1][col] = (int) EMPTY;
                dfs(row - 2, col);
            } else if (dir == 1 && row < wiersz - 2 && Map[row + 2][col] == (int) WALL) {
                // Idź w dół
                Map[row + 1][col] = (int) EMPTY;
                dfs(row + 2, col);
            } else if (dir == -1 && col > 2 && Map[row][col - 2] == (int) WALL) {
                // Idź w lewo
                Map[row][col - 1] = (int) EMPTY;
                dfs(row, col - 2);
            } else if (dir == 1 && col < kolumna - 2 && Map[row][col + 2] == (int) WALL) {
                // Idź w prawo
                Map[row][col + 1] = (int) EMPTY;
                dfs(row, col + 2);
            }
        }
    }

    private void randomShuffle(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public void tworzeniePacman() {

        int startX = 1;
        int startY = 1;
        data[startY][startX] = EMPTY;

        int pacmanStartX = 1;
        int pacmanStartY = 1;
        data[pacmanStartY][pacmanStartX] = PACMAN;

        pacmanPosition = new Point(pacmanStartX, pacmanStartY);

        for (int i = 0; i < wiersz; i++) {
            for (int j = 0; j < kolumna; j++) {
                if ((data[i][j] != WALL) && ((i != pacmanStartX) || (j != pacmanStartY))) {
                    data[i][j] = COIN;
                }
            }
        }
    }

    private void przejscia() {
        Random random = new Random();
        int numWalls = (int) (0.2 * (wiersz * kolumna));
        for (int i = 0; i < numWalls; i++) {
            int randomRow = random.nextInt(wiersz);
            int randomCol = random.nextInt(kolumna);
            if (data[randomRow][randomCol] == WALL) {
                data[randomRow][randomCol] = EMPTY;
            }
        }
    }


    private void sciany() {
        for (int col = 0; col < kolumna; col++) {
            Map[0][col] = (int) WALL;
            Map[wiersz - 1][col] = (int) WALL;
            data[0][col] = WALL;
            data[wiersz - 1][col] = WALL;
        }
        for (int row = 0; row < wiersz; row++) {
            Map[row][0] = (int) WALL;
            Map[row][kolumna - 1] = (int) WALL;
            data[row][0] = WALL;
            data[row][kolumna - 1] = WALL;
        }
    }


    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
        System.out.println("Updated value at (" + wiersz + ", " + kolumna + "): " + value);
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pacman.movePacman(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                pacman.movePacman(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                pacman.movePacman(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                pacman.movePacman(1, 0);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}









