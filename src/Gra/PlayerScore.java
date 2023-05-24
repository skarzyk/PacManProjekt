package Gra;


import javax.swing.*;
import java.util.Comparator;


public class PlayerScore {

    public static Comparator<? super PlayerScore> scoreComparator;
    private HighScoreManager highScoreManager;
    private String playerName;
    private int score;

    public PlayerScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void gameOver() {

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

