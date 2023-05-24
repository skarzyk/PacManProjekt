package Gra;
import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

import static Gra.PlanszaTableModel.*;

public class Pacman {
    private Point position;
    private PlanszaTableModel plansza;

    private OknoGra oknoGra;
    private int score = 0;
    private HighScoreManager highScoreManager;

    public Pacman(Point startPosition, PlanszaTableModel plansza) {
        this.position = startPosition;
        this.plansza = plansza;

    }



    public Point getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }

    public void movePacman(int dx, int dy) {
        System.out.println("Trying to move Pacman by: " + dx + ", " + dy);

        Point newPosition = new Point(position.x + dx, position.y + dy);
        if (canMoveTo(newPosition)) {
            System.out.println("Pacman can move to new position: " + newPosition);

            Object oldPositionValue = plansza.getValueAt(position.y, position.x);


            if (plansza.getValueAt(newPosition.y, newPosition.x).equals(COIN)) {
                score++;
                System.out.println("Score: " + score);

            }

            plansza.setValueAt(oldPositionValue.equals(COIN) ? COIN : EMPTY, position.y, position.x);
            position = newPosition;
            plansza.setValueAt(PACMAN, position.y, position.x);

        } else {
            System.out.println("Pacman cannot move to new position: " + newPosition);
        }
        plansza.fireTableDataChanged();
    }


    private boolean canMoveTo(Point newPosition) {

        if (!plansza.getValueAt(newPosition.y, newPosition.x).equals(WALL)) {
            return true;
        } else {
            return false;
        }
    }
    public void gameOver(int score) {

        String playerName = JOptionPane.showInputDialog("Game Over! Please enter your name:");
        if (playerName == null) {
            playerName = "Unknown";
        }


        highScoreManager.addScore(playerName, score);


        Comparator<PlayerScore> scoreComparator = new Comparator<PlayerScore>() {
            public int compare(PlayerScore s1, PlayerScore s2) {
                return s2.getScore() - s1.getScore();
            }
        };
    }


}



