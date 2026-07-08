package libraryproject;

// ui for the pop-up window

import javax.swing.*;       // the asterisk is to import all classes instead of individually typing stuff out
import java.awt.*;
import java.util.ArrayList;

public class Dialog {
    private static JButton ok = new JButton( "OK");
    private static JButton cancel = new JButton("Cancel");
    private static JPanel buttonPanel = new JPanel();

    public Dialog(JFrame frame, Manager manager, JTextArea Allbooks, String title) {
        JDialog dialog = new JDialog(frame, title, true);      // JDialog = a window that "blocks" the main window; true = modal (user must respond to this first)

        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // padding

        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        JTextField titleField = new JTextField(20);    // 20 chars (width)
        JTextField authorField = new JTextField(20);
        JTextField genreField = new JTextField(20);
        JTextField copiesField = new JTextField(5);

        gbc.gridx = 0; gbc.gridy = 0; dialog.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; dialog.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; dialog.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1; dialog.add(authorField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; dialog.add(new JLabel("Genre:"), gbc);
        gbc.gridx = 1; dialog.add(genreField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; dialog.add(new JLabel("Copies:"), gbc);
        gbc.gridx = 1; dialog.add(copiesField, gbc);

        // Row 4: buttons span both columns (gridwidth = 2)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; dialog.add(buttonPanel, gbc);

        ok.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                String genre = genreField.getText().trim();
                int copies = Integer.parseInt(copiesField.getText().trim());

                if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // rerun action listener
                }

                manager.getBooks().add(new Book(title, author, genre, copies));
                manager.saveBooks();
                JOptionPane.showMessageDialog(frame, "Book added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Copies must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancel.addActionListener(e -> dialog.dispose());   // close dialog

        dialog.pack();  // autosize the dialog size based on its contents
        dialog.setLocationRelativeTo(frame);  // center on parent frame
        dialog.setVisible(true);
    }
}