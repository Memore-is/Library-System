package libraryproject;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Library!");

        System.out.print("Please enter your ID: ");
        int ID = input.nextInt(); 

        if (ID == 12345) { 
            System.out.println("Welcome, Librarian!");
            // Librarian functionalities can be added here
        } else {
            System.out.println("Welcome, Student!");
            // Student functionalities can be added here
        }

        System.out.println("Would you like to borrow a book? (yes/no)");
        String choice = input.nextLine();
    }
}