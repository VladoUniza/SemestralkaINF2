package menu;

import level.Era;
import main.Starter;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StartingMenu {
    private JPanel panel;
    private JTextField nameTextField;
    private JButton newGameButton;
    private JButton loadGameButton;

    private static String nameOfTheUser;

    public StartingMenu() {
        JFrame frame = new JFrame("Mini Menu");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.newGameButton.addActionListener(_ -> {
            String name = this.nameTextField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name field cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            nameOfTheUser = name;
            Starter starter = new Starter();
            starter.startNewGame();

            frame.dispose();
        });

        this.loadGameButton.addActionListener(_ -> {
            File file = new File("gameData.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Game data is missing", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Scanner scanner = new Scanner(file)) {
                String name = "";
                int goldSpent = 0;
                int kills = 0;
                int era = 1;

                while (scanner.hasNextLine()) {
                    String newLine = scanner.nextLine();
                    String[] partsOfFile = newLine.split(": ");
                    if (partsOfFile.length < 2) {
                        continue;
                    }

                    switch (partsOfFile[0].trim()) {
                        case "Meno":
                            name = partsOfFile[1].trim();
                            break;
                        case "GoldSpent":
                            goldSpent = Integer.parseInt(partsOfFile[1].trim());
                            break;
                        case "Kills":
                            kills = Integer.parseInt(partsOfFile[1].trim());
                            break;
                        case "Era":
                            era = Era.valueOf(partsOfFile[1].trim()).getNumber();
                            break;
                    }
                }

                nameOfTheUser = name;
                Starter starter = new Starter();
                starter.loadGame(goldSpent, kills, era);

                JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(this.panel);
                topFrame.dispose();

            } catch (IOException | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Loaded data has been corrupted!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static String getName() {
        return nameOfTheUser;
    }
}