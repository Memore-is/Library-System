package libraryproject;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private ArrayList<Book> books = new ArrayList<Book>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void loadBooks() {
        File file = new File("books.txt");
        if (!file.exists()) {
            file = new File("Library System/src/libraryproject/books.txt");
        }

        if (!file.exists()) {
            System.err.println("Could not find books.txt. Checked paths: \n  " +
                    new File("books.txt").getAbsolutePath() + "\n  " +
                    file.getAbsolutePath());
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                if (parts.length < 4) {
                    continue;
                }

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

    public void saveBooks() {
        File file = new File("books.txt");
        if (!file.exists()) {
            file = new File("Library System/src/libraryproject/books.txt");
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Book book : books) {
                writer.println(book.getTitle() + "|" + book.getAuthor() + "|" + book.getGenre() + "|" + book.getCopies());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
