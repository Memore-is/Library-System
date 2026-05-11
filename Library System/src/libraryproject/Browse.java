package libraryproject;

import java.util.ArrayList;
import java.util.Scanner;

public class Browse {
    private ArrayList<Book> books = new ArrayList<>();

    public Browse() {
        loadBooks();
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book);
        }
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
}
