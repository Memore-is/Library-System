package libraryproject;

import java.util.ArrayList;

public class Library {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<Book> ();

        books.add(new Book("1984", "George Orwell", "Dystopian Fiction"));
        // books.remove(0);
        books.add(new Book("Animal Farm", "George Orwell", "Political Satire"));

        // books.set(0, 45);  // only if there is an existing object at specified index (replacing)

        books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", "Fiction"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "Classic"));

        System.out.println(books);                // gets id num (for objects)
        System.out.println(books.get(1));  // same 

        //sort by author names in slphabetical order
    }
}
