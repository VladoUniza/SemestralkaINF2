package Menu;

import Level.Era;
import Main.Starter;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class StartingMenu {
    private JPanel panel;
    private JTextField menoField;
    private JButton potvrdButton;
    private JButton loadGameButton;

    private static String nameOfTheUser;

    public StartingMenu() {
        JFrame frame = new JFrame("Mini Menu");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        potvrdButton.addActionListener(_ -> {
            String name = menoField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name field cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            nameOfTheUser = name;
            Starter starter = new Starter();
            starter.spusti();

            frame.dispose();
        });

        loadGameButton.addActionListener(_ -> {
            File subor = new File("gameData.txt");
            if (!subor.exists()) {
                JOptionPane.showMessageDialog(null, "Game data is missing", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Scanner scanner = new Scanner(subor)) {
                String name = "";
                int goldSpent = 0;
                int kills = 0;
                int era = 1;

                while(scanner.hasNextLine()) {
                    String riadok = scanner.nextLine();
                    String[] casti = riadok.split(": ");
                    if (casti.length < 2) {
                        continue;
                    }

                    switch (casti[0].trim()) {
                        case "Meno":
                            name = casti[1].trim();
                            break;
                        case "GoldSpent":
                            goldSpent = Integer.parseInt(casti[1].trim());
                            break;
                        case "Kills":
                            kills = Integer.parseInt(casti[1].trim());
                            break;
                        case "Era":
                            era = Era.valueOf(casti[1].trim()).getNumber();
                            break;
                    }
                }

                nameOfTheUser = name;
                Starter starter = new Starter();
                starter.nacitajProgres(goldSpent, kills, era);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
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