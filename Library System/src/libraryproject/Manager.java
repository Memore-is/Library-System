package libraryproject;

// loads books from txt file to program and vice versa to save changes

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public Manager() {
        loadBooks();
        loadStudents();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void loadBooks() {
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

    public void loadStudents() {
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

                String Fname = parts[0].trim();
                String Lname = parts[1].trim();
                int osis = Integer.parseInt(parts[2].trim());
                
                String borrowed = parts[3].trim();
                String borrowedBooks[] = borrowed.split("|");


                students.add(new Student(Fname + " " + Lname, osis));
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
            for (Student student : students) {
                writer.println(student.getName() + "|" + student.getOsis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}