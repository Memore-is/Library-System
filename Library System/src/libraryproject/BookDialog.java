package libraryproject;

// ui for the pop-up window

import javax.swing.*;       // the asterisk is to import all classes instead of individually typing stuff out
import java.awt.*;
import java.util.ArrayList;

public class BookDialog {
    public static void showAddBookDialog(JFrame frame, Manager manager, JTextArea Allbooks) {
        // JDialog = a window that "blocks" the main window (you must close it first)
        // "Add Book" = dialog title
        // true = modal (user must respond to this dialog before using main window)
        JDialog dialog = new JDialog(frame, "Add Book", true);
        
        // GridBagLayout = lets you position components in a grid with precise control
        // (much more flexible than FlowLayout)
        dialog.setLayout(new GridBagLayout());
        
        // GridBagConstraints = settings for how components should be placed in the grid
        GridBagConstraints gbc = new GridBagConstraints();
        // Add 5 pixels of space around each component (padding)
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create 4 text input fields
        JTextField titleField = new JTextField(20);    // 20 chars wide
        JTextField authorField = new JTextField(20);
        JTextField genreField = new JTextField(20);
        JTextField copiesField = new JTextField(5);

        // Row 0: Label "Title:" in column 0, input field in column 1
        gbc.gridx = 0; gbc.gridy = 0; dialog.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; dialog.add(titleField, gbc);

        // Row 1: Label "Author:" in column 0, input field in column 1
        gbc.gridx = 0; gbc.gridy = 1; dialog.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1; dialog.add(authorField, gbc);

        // Row 2: Label "Genre:" in column 0, input field in column 1
        gbc.gridx = 0; gbc.gridy = 2; dialog.add(new JLabel("Genre:"), gbc);
        gbc.gridx = 1; dialog.add(genreField, gbc);

        // Row 3: Label "Copies:" in column 0, input field in column 1
        gbc.gridx = 0; gbc.gridy = 3; dialog.add(new JLabel("Copies:"), gbc);
        gbc.gridx = 1; dialog.add(copiesField, gbc);

        // Create OK and Cancel buttons
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // Create a panel to hold the buttons side-by-side
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Row 4: buttons span both columns (gridwidth = 2)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; dialog.add(buttonPanel, gbc);

        // ===== OK BUTTON ACTION =====
        okButton.addActionListener(e -> {
            try {
                // Get text from all fields and trim whitespace
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                String genre = genreField.getText().trim();
                // Convert copies string to integer (could fail if not a number)
                int copies = Integer.parseInt(copiesField.getText().trim());

                // Check that no fields are empty
                if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, 
                        "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Don't add the book, let user try again
                }

                // All data is valid, create a new Book and add it to the list
                manager.getBooks().add(new Book(title, author, genre, copies));
                manager.saveBooks();
                // Tell user the book was added
                JOptionPane.showMessageDialog(frame, "Book added.");
                // Update the browse display to show the new book
                refreshAllBooks(manager.getBooks(), Allbooks);
                // Close the dialog
                dialog.dispose();
            } catch (NumberFormatException ex) {
                // User entered non-numeric text in the "Copies" field
                JOptionPane.showMessageDialog(dialog, 
                    "Copies must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ===== CANCEL BUTTON ACTION =====
        // Simply close the dialog without adding anything
        cancelButton.addActionListener(e -> dialog.dispose());

        // Resize dialog to fit components snugly (auto-size)
        dialog.pack();
        // Center the dialog on top of the main window
        dialog.setLocationRelativeTo(frame);
        // Show the dialog (blocks main window until closed)
        dialog.setVisible(true);
    }

    private static void refreshAllBooks(ArrayList<Book> books, JTextArea Allbooks) {
        String text = "";
        for (Book b : books) {
            text += b.toString() + "\n";
        }
        Allbooks.setText(text);
    }
}
