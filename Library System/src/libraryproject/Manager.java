package libraryproject;

// loads books from txt file to program

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public Manager() {      // constructor
        loadBooks();
        loadStudents();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    private void loadBooks() {
        File bookFile = new File("books.txt");
        try (Scanner scanner = new Scanner(bookFile)) {
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

    private void loadStudents() {
        File studentFile = new File("students.txt");

        try (Scanner scanner = new Scanner(studentFile)) {
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

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Book book : books) {
                writer.println(book.getTitle() + "|" + book.getAuthor() + "|" + book.getGenre() + "|" + book.getCopies());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStudents() {
        File file = new File("students.txt");

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Book book : books) {
                writer.println(book.getTitle() + "|" + book.getAuthor() + "|" + book.getGenre() + "|" + book.getCopies());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// the manager doesnt need to load these twice does it? i can just load them once in the constructor and then call the getBooks and getStudents methods to access them in the other classes. also, the saveBooks and saveStudents methods should be called whenever we make changes to the books or students lists, not just when we load them.
// 