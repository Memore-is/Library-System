package libraryproject; 

import java.util.ArrayList; 

public class Student {
    private String Fname, Lname;
    private int osis;
    private ArrayList<Book> borrowed;

    public Student(String Fname, String Lname, int osis) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.osis = osis;
        borrowed = new ArrayList<Book>();
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getName() {
        return Fname + " " + Lname;
    }

    public int getOsis() {
        return osis;
    }

    public ArrayList<Book> getBorrowed() {
        return borrowed;
    }

    public void borrowBook(Book book) { 
        if (book.checkAvailable()) {
            borrowed.add(book);
            book.setCopies(book.getCopies() - 1);
        }
    }

    public void returnBook(Book book) {
        if (borrowed.contains(book)) {
            borrowed.remove(book);
            book.setCopies(book.getCopies() + 1);
        }
    }
}