package libraryproject;

// Swing imports: javax.swing.* = GUI components (buttons, dialogs, text areas)
import javax.swing.*;
// AWT imports: java.awt.* = layout managers and basic GUI utilities
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * LibraryGUI - A Java Swing application for managing a library
 * This class creates a graphical user interface with buttons to browse, borrow, 
 * return, and add books. All data is loaded from and saved to books.txt
 */
public class LibraryGUI {

    // JFrame = the main window that holds all UI components
    JFrame frame;

    // JTextArea = a large text display area (read-only in this case)
    // Shows the list of books when user clicks "Browse"
    JTextArea bookArea;

    // JButton = clickable buttons for different actions
    JButton browseButton;      // Displays all available books
    JButton borrowButton;      // Reduces book copies by 1
    JButton returnButton;      // Increases book copies by 1
    JButton addButton;         // Opens a form to add a new book

    // ArrayList = dynamic list that stores all Book objects loaded from books.txt
    ArrayList<Book> books = new ArrayList<Book>();

    /**
     * Constructor: Runs when you do new LibraryGUI()
     * Sets up the entire window and all interactive elements
     */
    public LibraryGUI() {

        // Load all books from books.txt into the books ArrayList
        loadBooks();

        // Create the main window with title "Library System"
        frame = new JFrame("Library System");

        // Create the four action buttons
        browseButton = new JButton("Browse Books");
        borrowButton = new JButton("Borrow Book");
        returnButton = new JButton("Return Book");
        addButton = new JButton("Add Book");

        // Create a text area with 15 rows and 35 columns
        bookArea = new JTextArea(15, 35);
        // Don't allow the user to type in this area (read-only)
        bookArea.setEditable(false);

        // FlowLayout = arranges components left-to-right, top-to-bottom
        // (simpler than GridBagLayout, good for small GUIs)
        frame.setLayout(new FlowLayout());

        // Add the buttons to the window in order
        frame.add(browseButton);
        frame.add(borrowButton);
        frame.add(returnButton);
        frame.add(addButton);

        // Add the text area wrapped in a JScrollPane
        // (JScrollPane = adds scroll bars if text overflows)
        frame.add(new JScrollPane(bookArea));

        // ===== BROWSE BUTTON =====
        // addActionListener = "when user clicks this button, run this code"
        // e -> { } = lambda expression (shorthand for anonymous function)
        browseButton.addActionListener(e -> {

            // Build a string with all books
            String text = "";

            // Loop through each Book object in the books list
            for (Book b : books) {
                // Call the book's toString() method to get formatted text like:
                // "1984 by George Orwell (Classics, Available)"
                text += b.toString() + "\n";
            }

            // Display the text in the text area
            bookArea.setText(text);
        });

        // ===== BORROW BUTTON =====
        // When clicked, ask user which book they want to borrow
        borrowButton.addActionListener(e -> {

            // Pop up a dialog box that lets user type in a book title
            String title = JOptionPane.showInputDialog(frame, "Enter book title:");

            // Loop through all books to find a match
            for (Book b : books) {

                // Check if this book's title matches (case-insensitive)
                if (b.getTitle().equalsIgnoreCase(title)) {

                    // Is the book available (more than 0 copies)?
                    if (b.checkAvailable()) {
                        // Reduce the copy count by 1
                        b.setCopies(b.getCopies() - 1);

                        // Show success message
                        JOptionPane.showMessageDialog(frame,
                                "You borrowed " + b.getTitle());
                        // Update the display to show new copy count
                        refreshBookArea();
                    } else {
                        // No copies available
                        JOptionPane.showMessageDialog(frame,
                                "Book unavailable.");
                    }

                    // Exit the loop (we found the book)
                    return;
                }
            }

            // If we get here, the book was not found
            JOptionPane.showMessageDialog(frame,
                    "Book not found.");
        });

        // ===== RETURN BUTTON =====
        // Similar to borrow, but adds a copy back instead of removing one
        returnButton.addActionListener(e -> {

            String title = JOptionPane.showInputDialog(frame, "Enter book title:");

            for (Book b : books) {

                if (b.getTitle().equalsIgnoreCase(title)) {

                    // Increase the copy count by 1
                    b.setCopies(b.getCopies() + 1);

                    JOptionPane.showMessageDialog(frame,
                            "Book returned.");
                    // Update the display to show new copy count
                    refreshBookArea();

                    return;
                }
            }

            JOptionPane.showMessageDialog(frame,
                    "Book not found.");
        });

        // ===== ADD BOOK BUTTON =====
        // When clicked, open a special dialog form (not just a simple input box)
        addButton.addActionListener(e -> showAddBookDialog());

        // Set the window size to 500 pixels wide by 400 pixels tall
        frame.setSize(500, 400);
        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // When user clicks the X button, close the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the window visible (must be last line in constructor)
        frame.setVisible(true);
    }

    /**
     * showAddBookDialog() - Creates a custom dialog form for adding books
     * Instead of 4 separate pop-ups, this shows one form with 4 input fields
     */
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

    /**
     * main() - Entry point of the program
     * This method is called when user runs: java -jar LibrarySystem.jar
     */
    public static void main(String[] args) {
        // SwingUtilities.invokeLater() = "run this code on the Swing event thread"
        // This is required for thread safety in Swing applications
        // Without it, the GUI might have weird glitches on some systems
        // The lambda () -> new LibraryGUI() means: "create the GUI"
        javax.swing.SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}