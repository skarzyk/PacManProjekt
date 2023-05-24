package Gra;

import ActionListener.Exit;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoMenu extends JFrame{
    private int szerokoscPlanszy;
    private int wysokoscPlanszy;
    HighScoreManager highScoreManager = new HighScoreManager();
    List<PlayerScore> topScores;
    public OknoMenu() {
        generateFrame();
    }

    public void generateFrame(){


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));


        JButton b1 = new JButton("New game");
        b1.setBackground(Color.lightGray);
        b1.setForeground(Color.darkGray);

        JButton b2= new JButton("Highscore");
        b2.setBackground(Color.lightGray);
        b2.setForeground(Color.darkGray);

        JButton b3 = new JButton("Exit");
        b3.addActionListener(new Exit());
        b3.setBackground(Color.lightGray);
        b3.setForeground(Color.darkGray);

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);

        add(panel);
        setSize(300, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new OknoGra());

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<PlayerScore> topScores = highScoreManager.getTopScores(10);
                new HighScoreWindow(topScores);
            }
        });
    }
}



