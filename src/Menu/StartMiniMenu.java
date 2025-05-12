package Menu;

import Main.Starter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMiniMenu {
    private JPanel panel;
    private JButton potvrdButton;
    private JTextField menoField;

    private static String nameOfTheUser;

    public StartMiniMenu() {
        JFrame frame = new JFrame("Mini Menu");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        potvrdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meno = menoField.getText().trim();
                if (meno.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Zadaj svoje meno!", "Upozornenie", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                nameOfTheUser = meno;
                Starter starter = new Starter();
                starter.spusti();

                frame.dispose();
            }
        });
    }

    public static String getName() {
        return nameOfTheUser;
    }
}