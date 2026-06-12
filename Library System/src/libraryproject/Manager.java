package libraryproject;

// loads books from txt file to program

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Book> getStudents() {
        return books;
    }

    public void loadArrays() {
        File bookFile = new File("books.txt");
        File studentFile = new File("students.txt");

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
