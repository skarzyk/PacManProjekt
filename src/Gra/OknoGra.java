package Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OknoGra extends JFrame {

    private int score;
    private Pacman pacman;
    private  HighScoreManager highScoreManager;

    private JLabel label;


    public OknoGra() {
        super("PacMan");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int wysokoscPlanszy = getDimensionFromUser("Podaj wysokosc planszy (10-100):");
        int szerokoscPlanszy = getDimensionFromUser("Podaj szerokosc planszy (10-100):");

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        PlanszaTableModel planszaTableModel = new PlanszaTableModel(szerokoscPlanszy, wysokoscPlanszy);


        JTable table = new JTable(planszaTableModel);
        table.setDefaultRenderer(Object.class, new PlanszaTableCellRenderer());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setTableHeader(null);

        table.addKeyListener(planszaTableModel);
        table.setFocusable(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(scrollPane);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        label = new JLabel("Score: 0");
        frame.add(label, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);





        KeyStroke keyStroke = KeyStroke.getKeyStroke("control shift Q");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "closeWindow");
        getRootPane().getActionMap().put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = JOptionPane.showInputDialog("Game Over! Please enter your name:");
                if (playerName == null) {
                    playerName = "Unknown";
                }
                int playerScore = pacman.getScore();

                PlayerScore playerScoreObj = new PlayerScore(playerName, playerScore);
                highScoreManager.savePlayerScore(playerScoreObj);

                dispose();
                SwingUtilities.invokeLater(() -> new Menu());
            }
        });
    }






    private int getDimensionFromUser(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(this, message);
                int dimension = Integer.parseInt(input);
                if (dimension >= 10 && dimension <= 100) {
                    return dimension;
                } else {
                    JOptionPane.showMessageDialog(this, "Wartości powinny być w zakresie od 10 do 100!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Wprowadź poprawne wartości!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }




}








