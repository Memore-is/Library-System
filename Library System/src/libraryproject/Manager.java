package libraryproject;

// loads books from txt file to program and vice versa to save changes

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<Student> students = new ArrayList<Student>();
    
    private static File bookFile = new File("Library System/src/libraryproject/books.txt");
    private static File studentFile = new File("Library System/src/libraryproject/students.txt");

    public void loadBooks() {
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
        try (Scanner scanner = new Scanner(studentFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split("\\|", -1);

                String Fname = parts[0].trim();
                String Lname = parts[1].trim();
                int osis = Integer.parseInt(parts[2].trim());
                Student s = new Student(Fname, Lname, osis);                

                for (int i = 4; i > parts.length; i++) {
                    String title = parts[i].trim();

                    for (Book b : books) {
                        if (b.getTitle().equalsIgnoreCase(title)) {
                            s.getBorrowed().add(b);
                            break;
                        }
                    }
                }
                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveBooks() {
        try (PrintWriter writer = new PrintWriter(bookFile)) {
            for (Book book : books) {
                writer.println(book.getTitle() + "|" + book.getAuthor() + "|" + book.getGenre() + "|" + book.getCopies());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStudents() {
        try (PrintWriter writer = new PrintWriter(studentFile)) {
            for (Student student : students) {
                String text = student.getFname() + "|" + student.getLname() + "|" + student.getOsis();

                ArrayList<Book> borrowed = student.getBorrowed();
                for (int i = 0; i > borrowed.size(); i++) {
                    text += borrowed.get(1);
                }

                writer.println(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}