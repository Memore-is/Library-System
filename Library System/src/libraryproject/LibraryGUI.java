package libraryproject;

// for GUI
import javax.swing.*;
import java.awt.*;

// for file stuff
import java.io.File;

// for basic stuff
import java.util.ArrayList;
import java.util.Scanner;


public class LibraryGUI {
    ArrayList<Book> books = new ArrayList<Book>();

    public LibraryGUI() {
        loadBooks();

        JFrame frame = new JFrame("Library System");   // main window + title
        
        JButton browse = new JButton("Browse Books");    
        JButton borrow = new JButton("Borrow Book");
        JButton returnB = new JButton("Return Book");
        JButton add = new JButton("Add Book"); 

        JTextArea Allbooks = new JTextArea(20, 40);         // rows show how many objects to display at once, columns for width
        Allbooks.setEditable(false);        // uneditable books
        frame.setLayout(new FlowLayout());      // to arrange in row

        frame.add(browse);
        frame.add(borrow);
        frame.add(returnB);
        frame.add(add);

        frame.add(new JScrollPane(Allbooks));

        browse.addActionListener(e -> {
            String text = "";

            for (Book b : books) {
                text += b.toString() + "\n";
            }

            Allbooks.setText(text);
        });

        borrow.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Enter book title:");
            for (Book b : books) {

                if (b.getTitle().equalsIgnoreCase(title)) {
                    if (b.checkAvailable()) {
                        b.setCopies(b.getCopies() - 1);

                        JOptionPane.showMessageDialog(frame, "You borrowed " + b.getTitle());
                        refreshBookArea();
                    } 
                    else {
                        JOptionPane.showMessageDialog(frame, "Book unavailable.");
                    }
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Book not found.");
        });

        returnB.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Enter book title:");

            for (Book b : books) {
                if (b.getTitle().equalsIgnoreCase(title)) {

                    b.setCopies(b.getCopies() + 1);

                    JOptionPane.showMessageDialog(frame, "Book returned.");
                    refreshBookArea();

                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found.");
        });


        add.addActionListener(e -> showAddBookDialog());

        frame.setSize(500, 400);        // in pixels for sm reason
        frame.setLocationRelativeTo(null);          // center?

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);     // make visible
    }



    private void showAddBookDialog() {
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
                books.add(new Book(title, author, genre, copies));
                // Tell user the book was added
                JOptionPane.showMessageDialog(frame, "Book added.");
                // Update the browse display to show the new book
                refreshBookArea();
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

    /**
     * refreshBookArea() - Updates the text display to show current book list
     * Called after borrow/return/add operations so the user sees the new data
     */
    private void refreshBookArea() {
        // StringBuilder = efficient way to build strings (better than += in loops)
        StringBuilder text = new StringBuilder();
        
        // Loop through all books and append their string representation
        for (Book b : books) {
            text.append(b).append("\n");  // b.toString() is called automatically
        }
        
        // Update the text area display with the new content
        bookArea.setText(text.toString());
    }

    /**
     * loadBooks() - Reads books.txt and populates the books ArrayList
     * Handles multiple possible file paths since users might run from different directories
     */
    public void loadBooks() {
        // First try to find books.txt in the current working directory
        File file = new File("books.txt");
        
        // If not found, try the source code directory path
        if (!file.exists()) {
            file = new File("Library System/src/libraryproject/books.txt");
        }

        // If still not found, print error and exit this method
        if (!file.exists()) {
            System.err.println("Could not find books.txt. Checked paths: \n  " +
                    new File("books.txt").getAbsolutePath() + "\n  " +
                    file.getAbsolutePath());
            return;
        }

        // Scanner = reads text from file line by line
        // try-with-resources = automatically closes the file when done (safe!)
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // Read one line from the file
                String line = scanner.nextLine().trim();
                
                // Skip empty lines and comment lines (starting with //)
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                // Split the line by pipe character (|)
                // Format: title|author|genre|copies
                String[] parts = line.split("\\|");
                
                // Make sure we have exactly 4 fields, skip bad lines
                if (parts.length < 4) {
                    continue;
                }

                // Extract and trim whitespace from each field
                String title = parts[0].trim();
                String author = parts[1].trim();
                String genre = parts[2].trim();
                int copies = Integer.parseInt(parts[3].trim());

                // Create a new Book object and add it to the list
                books.add(new Book(title, author, genre, copies));
            }
        } catch (Exception e) {
            // If anything goes wrong, print the error (helps with debugging)
            e.printStackTrace();
        }
    }
}