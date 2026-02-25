package libraryproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Library {
    Scanner input = new Scanner(System.in);
    ArrayList<Book> books = new ArrayList<Book> ();  // store type of data in arraylist (Book objects)

    public Library() {
        System.out.print("Enter the number of books to add to the library: ");
        int numBooks = input.nextInt();
        input.nextLine();  // consume the newline character

        for (int i = 0; i < numBooks; i++) {
            System.out.print("Enter the title of book " + (i + 1) + ": ");
            String title = input.nextLine();

            System.out.print("Enter the author of book " + (i + 1) + ": ");
            String author = input.nextLine();

            System.out.print("Enter the genre of book " + (i + 1) + ": ");
            String genre = input.nextLine();

            System.out.print("Enter the quantity of book " + (i + 1) + ": ");
            int copies = input.nextInt();
  
            books.add(new Book(title, author, genre, copies)); 
        }
        BaseBooks();

    }

// add 5 copies each book as base parameter in Book constructor and then add method to check out book (decrease quantity by 1 and set availability to false if quantity is 0) and return book (increase quantity by 1 and set availability to true)
// remove duplicate books manually (same title and author) and add method to check if book already exists in library before adding new book (if it does, just increase quantity)

        public void BaseBooks() {
            books.add(new Book("1984", "George Orwell", "Classics", 5));
            books.add(new Book("Animal Farm", "George Orwell", "Classics", 5));
            books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", "Classics", 5));
            books.add(new Book("Pride and Prejudice", "Jane Austen", "Classics", 5));
            books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classics", 5));
            books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Classics", 5));
            books.add(new Book("The Brothers Karamazov", "Fyodor Dostoevsky", "Classics", 5));
            books.add(new Book("Anna Karenina", "Leo Tolstoy", "Classics", 5));
            books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Classics", 5));
            books.add(new Book("Frankenstein", "Mary Shelley", "Classics", 5));
            books.add(new Book("The Odyssey", "Homer", "Classics", 5));
            books.add(new Book("The Iliad", "Homer", "Classics", 5));
            books.add(new Book("Les Misérables", "Victor Hugo", "Classics", 5));
            books.add(new Book("The Count of Monte Cristo", "Alexandre Dumas", "Classics", 5));
            books.add(new Book("Slaughterhouse-Five", "Kurt Vonnegut", "Classics", 5));
            books.add(new Book("The Grapes of Wrath", "John Steinbeck", "Classics", 5));

            books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 5));
            books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", 5));
            books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 5));
            books.add(new Book("A Game of Thrones", "George R.R. Martin", "Fantasy", 5));
            books.add(new Book("The Name of the Wind", "Patrick Rothfuss", "Fantasy", 5));
            books.add(new Book("The Lies of Locke Lamora", "Scott Lynch", "Fantasy", 5));
            books.add(new Book("The Way of Kings", "Brandon Sanderson", "Fantasy", 5));
            books.add(new Book("The Wheel of Time", "Robert Jordan", "Fantasy", 5));

            books.add(new Book("The Martian", "Andy Weir", "Science Fiction", 5));
            books.add(new Book("Dune", "Frank Herbert", "Science Fiction", 5));
            books.add(new Book("Ender's Game", "Orson Scott Card", "Science Fiction", 5));
            books.add(new Book("Foundation", "Isaac Asimov", "Science Fiction", 5));
            books.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Science Fiction", 5    ));

            books.add(new Book("The Da Vinci Code", "Dan Brown", "Mystery", 5));
            books.add(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "Mystery", 5));
            books.add(new Book("The Hound of the Baskesrville", "Arthur Conan Doyle", "Mystery", 5));
            books.add(new Book("A Study in Scarlet", "Arthur Conan Doyle", "Mystery", 5));
            books.add(new Book("The Sign of the Four", "Arthur Conan Doyle", "Mystery", 5));
            books.add(new Book("The Valley of Fear", "Arthur Conan Doyle", "Mystery", 5));
            books.add(new Book("Murder on the Orient Express", "Agatha Christie", "Mystery", 5));
            books.add(new Book("And Then There Were None", "Agatha Christie", "Mystery", 5));
            books.add(new Book("The Moving Finger", "Agatha Christie", "Mystery", 5));
            books.add(new Book("Crooked House", "Agatha Christie", "Mystery", 5));
            books.add(new Book("Nemesis", "Agatha Christie", "Mystery", 5));
            books.add(new Book("Partners in Crime", "Agatha Christie", "Mystery", 5));
            books.add(new Book("A Caribbean Mystery", "Agatha Christie", "Mystery", 5));
            books.add(new Book("The Murder at the Vicarage", "Agatha Christie", "Mystery", 5));
            books.add(new Book("The Man in the Brown Suit", "Agatha Christie", "Mystery", 5));
            books.add(new Book("The Case of the Missing Will", "Agatha Christie", "Mystery", 5));
            books.add(new Book("The Purloined Letter", "Edgar Allan Poe", "Mystery", 5));
            books.add(new Book("The Domain of Arachne", "Edgar Allan Poe", "Mystery", 5));
            books.add(new Book("The Murders in the Rue Morgue", "Edgar Allan Poe", "Mystery", 5));
            books.add(new Book("The Maltese Falcon", "Dashiell Hammett", "Mystery", 5));
            books.add(new Book("Gone Girl", "Gillian Flynn", "Mystery", 5));
            books.add(new Book("The Silence of the Lambs", "Thomas Harris", "Mystery", 5));
            books.add(new Book("The Balloon Hoax", "Edgar Allan Poe", "Mystery", 5));
            books.add(new Book("The Fall of the House of Usher", "Edgar Allan Poe", "Mystery", 5));
            books.add(new Book("The Tell-Tale Heart", "Edgar Allan Poe", "Mystery", 5));
            books.add(new Book("The Cask of Amontillado", "Edgar Allan Poe", "Mystery", 5));

            books.add(new Book("The Shining", "Stephen King", "Horror", 5));
            books.add(new Book("Dracula", "Bram Stoker", "Horror", 5));
            books.add(new Book("The Exorcist", "William Peter Blatty", "Horror", 5));
            books.add(new Book("It", "Stephen King", "Horror", 5));   
            books.add(new Book("The Haunting of Hill House", "Shirley Jackson", "Horror", 5));
            books.add(new Book("The Silence of the Lambs", "Thomas Harris", "Horror", 5));
            books.add(new Book("The Amityville Horror", "Jay Anson", "Horror", 5)); 
            books.add(new Book("The Turn of the Screw", "Henry James", "Horror", 5));
            books.add(new Book("The Call of Cthulhu", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Shadow over Innsmouth", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Dunwich Horror", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Colour Out of Space", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Whisperer in Darkness", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Shadow Out of Time", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Dreams in the Witch House", "H.P. Lovecraft", "Horror", 5));    
            books.add(new Book("The Horror at Red Hook", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Rats in the Walls", "H.P. Lovecraft", "Horror", 5));
            books.add(new Book("The Outsider", "Stephen King", "Horror", 5));
            books.add(new Book("The Green Mile", "Stephen King", "Horror", 5));
            books.add(new Book("Pet Sematary", "Stephen King", "Horror", 5));
            books.add(new Book("Carrie", "Stephen King", "Horror", 5));
            books.add(new Book("Misery", "Stephen King", "Horror", 5)); 
            books.add(new Book("The Stand", "Stephen King", "Horror", 5));
            books.add(new Book("The Dark Tower", "Stephen King", "Horror", 5));
            books.add(new Book("The Institute", "Stephen King", "Horror", 5));

            books.add(new Book("The Art of War", "Sun Tzu", "Non-Fiction", 5));
            books.add(new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Non-Fiction", 5));
            books.add(new Book("Educated", "Tara Westover", "Non-Fiction", 5));
            books.add(new Book("The Immortal Life of Henrietta Lacks", "Rebecca Skloot", "Non-Fiction", 5));
            books.add(new Book("The Wright Brothers", "David McCullough", "Non-Fiction", 5)); 
            books.add(new Book("The Diary of a Young Girl", "Anne Frank", "Non-Fiction", 5));  
            books.add(new Book("The Glass Castle", "Jeannette Walls", "Non-Fiction", 5));
            books.add(new Book("The Power of Habit", "Charles Duhigg", "Non-Fiction", 5));
            books.add(new Book("The Subtle Art of Not Giving a F*ck", "Mark Manson", "Non-Fiction", 5));
            books.add(new Book("Thinking, Fast and Slow", "Daniel Kahneman", "Non-Fiction", 5));
            books.add(new Book("The 7 Habits of Highly Effective People", "Stephen R. Covey", "Non-Fiction", 5));  
            books.add(new Book("How to Win Friends and Influence People", "Dale Carnegie", "Non-Fiction", 5));
            books.add(new Book("The Power of Now", "Eckhart Tolle", "Non-Fiction", 5));
            books.add(new Book("Man's Search for Meaning", "Viktor E. Frankl", "Non-Fiction", 5)); 

            books.add(new Book("Meno", "Plato", "Philosophy", 5));
            books.add(new Book("The Republic", "Plato", "Philosophy", 5));
            books.add(new Book("Nicomachean Ethics", "Aristotle", "Philosophy", 5));
            books.add(new Book("Meditations", "Marcus Aurelius", "Philosophy", 5));
            books.add(new Book("The Prince", "Niccolò Machiavelli", "Philosophy", 5));
            books.add(new Book("Beyond Good and Evil", "Friedrich Nietzsche", "Philosophy", 5));
            books.add(new Book("The Art of Happiness", "Dalai Lama", "Philosophy", 5));   
            books.add(new Book("The Tao of Pooh", "Benjamin Hoff", "Philosophy", 5      ));
            books.add(new Book("The Book of Five Rings", "Miyamoto Musashi", "Philosophy", 5));
            books.add(new Book("The Analects", "Confucius", "Philosophy", 5));
            books.add(new Book("The Symposium", "Plato", "Philosophy", 5));
            books.add(new Book("The Stranger", "Albert Camus", "Philosophy", 5));  
            books.add(new Book("The Myth of Sisyphus", "Albert Camus", "Philosophy", 5));
            books.add(new Book("The Ethics", "Baruch Spinoza", "Philosophy", 5));
            books.add(new Book("The Social Contract", "Jean-Jacques Rousseau", "Philosophy", 5));  
            books.add(new Book("The Leviathan", "Thomas Hobbes", "Philosophy", 5));    
            books.add(new Book("The Critique of Pure Reason", "Immanuel Kant", "Philosophy", 5));  
            books.add(new Book("The Critique of Practical Reason", "Immanuel Kant", "Philosophy", 5));
            books.add(new Book("The Critique of Judgment", "Immanuel Kant", "Philosophy", 5));
            books.add(new Book("The Phenomenology of Spirit", "Georg Wilhelm Friedrich Hegel", "Philosophy", 5));  
            books.add(new Book("The Being and Nothingness", "Jean-Paul Sartre", "Philosophy", 5)); 
            books.add(new Book("Physics", "Aristotle", "Philosophy", 5));
            books.add(new Book("Metaphysics", "Aristotle", "Philosophy", 5));  
            books.add(new Book("On the Origin of Species", "Charles Darwin", "Philosophy", 5));    
            books.add(new Book("The Structure of Scientific Revolutions", "Thomas S. Kuhn", "Philosophy", 5)); 
            books.add(new Book("The Selfish Gene", "Richard Dawkins", "Philosophy", 5));   
            books.add(new Book("The Demon-Haunted World: Science as a Candle in the Dark", "Carl Sagan", "Philosophy", 5));    
            books.add(new Book("Cosmos", "Carl Sagan", "Philosophy", 5));  
            books.add(new Book("The Varieties of Religious Experience", "William James", "Philosophy", 5));  
            books.add(new Book("The Will to Power", "Friedrich Nietzsche", "Philosophy", 5));  
            books.add(new Book("The Birth of Tragedy", "Friedrich Nietzsche", "Philosophy", 5));   
            books.add(new Book("The Genealogy of Morals", "Friedrich Nietzsche", "Philosophy", 5));    
            books.add(new Book("The Sceptical Essays", "Friedrich Nietzsche", "Philosophy", 5));
        }

        
        // System.out.println(books.get(0).getTitle());  // gets title of first book
        // System.out.println(books.get(0).getGenre());  // gets genre of first book
        // System.out.println(books.get(0).tostring());  // gets title, author, genre, and availability of first book
        // System.out.println(books.get(0).isAvailable());  // gets availability of first book
        // books.get(0).setAvailable(false);  // sets availability of first book to false (checked out)
        System.out.println(books.size());  // gets size of library (number of books)
        // books.clear();  // removes all objects from list
        // books.remove(0);
        // books.set(0, 45);  // only if there is an existing object at specified index (replacing)
        // System.out.println(books); // gets id num (for objects)
        // System.out.println(books.get(1));  // same 

        // need to override tostring method in Book class to get readable output when printing book objects
         for (Book book : books) {
            System.out.println(book.toString());
        }  
        //sort by author names in slphabetical order
        Collections.sort(books, new Comparator<Book>() {
            @Override    
                public int compare(Book b1, Book b2) {
            return b1.getAuthor().compareTo(b2.getAuthor());
                }
            });

        // need to add a method to sort by genre and then by author name within each genre (or just sort by genre and then print each genre separately)

        // for (Book book : books) {
        //     System.out.println(book.getAuthor());
        
        input.close();
    }