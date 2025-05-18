package menu;

import hud.Gold;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;


public class Statistics {

    public Statistics(int enemiesKilled) {
        JFrame frame = new JFrame("Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 1));

        JLabel menoLabel = new JLabel("Player: " + StartingMenu.getName(), SwingConstants.CENTER);
        JLabel killsLabel = new JLabel("Number of enemies killed: " + enemiesKilled, SwingConstants.CENTER);
        JLabel goldLabel = new JLabel("Number of gold spent: " + Gold.getGoldSpent(), SwingConstants.CENTER);
        JButton exitButton = new JButton("Close statistics");

        exitButton.addActionListener(_ -> System.exit(0));

        frame.add(menoLabel);
        frame.add(killsLabel);
        frame.add(goldLabel);
        frame.add(exitButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
