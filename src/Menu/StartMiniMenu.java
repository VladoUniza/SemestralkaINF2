package Menu;

import Main.Starter;
import javax.swing.*;
import java.awt.*;

public class StartMiniMenu {
    private final JFrame frame;
    private final JTextField menoField;

    private static String nameOfTheUser;

    public StartMiniMenu() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        menoField = new JTextField();
        menoField.setMaximumSize(new Dimension(1000, 30));
        menoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        menoField.setHorizontalAlignment(JTextField.CENTER);

        JButton potvrdButton = new JButton("Potvrď");
        potvrdButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Zadaj meno:"));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(menoField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(potvrdButton);

        potvrdButton.addActionListener(e -> {
            String meno = menoField.getText().trim();
            if (meno.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Zadaj svoje meno!", "Upozornenie", JOptionPane.WARNING_MESSAGE);
                return;
            }

            nameOfTheUser = meno;
            Starter starter = new Starter();
            starter.spusti();

            frame.dispose();
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static String getName() {
        return nameOfTheUser;
    }
}