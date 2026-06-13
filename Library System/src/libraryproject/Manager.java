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
        if (!bookFile.exists()) {
            File alt = new File("src/libraryproject/books.txt");
            if (alt.exists()) bookFile = alt;
            else {
                File altRoot = new File("Library System/books.txt");
                if (altRoot.exists()) bookFile = altRoot;
            }
        }

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
        if (!studentFile.exists()) {
            File alt = new File("src/libraryproject/students.txt");
            if (alt.exists()) studentFile = alt;
            else {
                File altRoot = new File("Library System/students.txt");
                if (altRoot.exists()) studentFile = altRoot;
                else {
                    // create an empty students.txt in working dir so future runs won't fail
                    try {
                        studentFile.createNewFile();
                    } catch (Exception ex) {
                        // ignore creation failure; scanner below will throw if missing
                    }
                }
            }
        }

        try (Scanner scanner = new Scanner(studentFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                String[] parts = line.split("\\|", -1); // split on literal '|'

                if (parts.length == 2) {
                    // Format: Name|OSIS
                    String name = parts[0].trim();
                    int osis = Integer.parseInt(parts[1].trim());
                    students.add(new Student(name, osis));
                } else if (parts.length >= 3) {
                    // Format: Fname|Lname|OSIS|borrowed (borrowed optional)
                    String Fname = parts[0].trim();
                    String Lname = parts[1].trim();
                    int osis = Integer.parseInt(parts[2].trim());
                    Student s = new Student(Fname + " " + Lname, osis);

                    // parse borrowed list if present
                    if (parts.length >= 4) {
                        String borrowed = parts[3].trim();
                        if (!borrowed.isEmpty() && !borrowed.equalsIgnoreCase("null")) {
                            // handle a few common separators: comma, semicolon, pipe
                            String cleaned = borrowed;
                            if (cleaned.startsWith("[") && cleaned.endsWith("]")) {
                                cleaned = cleaned.substring(1, cleaned.length() - 1);
                            }
                            String[] items = cleaned.split("\\s*,\\s*|;|\\|", -1);
                            for (String it : items) {
                                String title = it.trim();
                                if (title.isEmpty()) continue;
                                // find matching book by title (case-insensitive)
                                for (Book b : books) {
                                    if (b.getTitle().equalsIgnoreCase(title)) {
                                        s.getBorrowed().add(b);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    students.add(s);
                } else {
                    // Unrecognized format; skip
                    continue;
                }
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