package Gra;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScoreWindow extends JFrame {
    public HighScoreWindow(List<PlayerScore> scores) {
        setLayout(new BorderLayout());

        String[] columnNames = {"Player Name", "Score"};
        Object[][] data = new Object[scores.size()][2];
        for (int i = 0; i < scores.size(); i++) {
            data[i][0] = scores.get(i).getPlayerName();
            data[i][1] = scores.get(i).getScore();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setTitle("High Scores");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
