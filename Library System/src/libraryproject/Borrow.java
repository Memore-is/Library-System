package libraryproject;

import java.util.ArrayList;
import java.util.Scanner;

public class Borrow {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public Borrow() {
        loadBooks();
        System.out.print("Enter the title of the book to borrow: ");
        String title = input.nextLine().trim();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.checkAvailable()) {
                    book.setCopies(book.getCopies() - 1);
                    System.out.println("You have borrowed: " + book.getTitle());
                    saveBooks();
                } else {
                    System.out.println("Book is not available.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void loadBooks() {
        // Similar to LibraryGUI.loadBooks()
        java.io.File file = new java.io.File("books.txt");
        if (!file.exists()) {
            file = new java.io.File("Library System/src/libraryproject/books.txt");
        }
        if (!file.exists()) {
            System.err.println("Could not find books.txt.");
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("//")) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 4) continue;
                String title = parts[0].trim();
                String author = parts[1].trim();
                String genre = parts[2].trim();
                int copies = Integer.parseInt(parts[3].trim());
                books.add(new Book(title, author, genre, copies));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveBooks() {
        java.io.File file = new java.io.File("books.txt");
        if (!file.exists()) {
            file = new java.io.File("Library System/src/libraryproject/books.txt");
        }
        try (java.io.PrintWriter writer = new java.io.PrintWriter(file)) {
            for (Book book : books) {
                writer.println(book.getTitle() + "|" + book.getAuthor() + "|" + book.getGenre() + "|" + book.getCopies());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
