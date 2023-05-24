package Gra;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreManager extends JFrame {
    private List<PlayerScore> scores;

    public void savePlayerScore(PlayerScore playerScore){
        scores.add(playerScore);
        saveScoresToFile();
    }
    public HighScoreManager() {
        scores = new ArrayList<>();
        scores.add(new PlayerScore("Anonim Gal", 17));
        scores.add(new PlayerScore("Michal Aniol", 55));
        loadScoresFromFile();
    }

    public void addScore(String playerName, int score) {
        scores.add(new PlayerScore(playerName, score));
        saveScoresToFile();
    }

    private void loadScoresFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highscores.dat"))) {
            scores = (ArrayList<PlayerScore>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading scores: " + e.getMessage());
        }
    }

    private void saveScoresToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highscores.dat"))) {
            oos.writeObject(scores);
        } catch (Exception e) {
            System.out.println("Error saving scores: " + e.getMessage());
        }
    }

    public List<PlayerScore> getTopScores(int top) {
        List<PlayerScore> topScores = new ArrayList<>(scores);
        Collections.sort(topScores, PlayerScore.scoreComparator);
        return topScores.subList(0, Math.min(top, topScores.size()));
    }
}
