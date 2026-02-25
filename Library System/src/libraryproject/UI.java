package libraryproject;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Library!");

        while (true) {
            System.out.println("Options: \n1. Borrow Book \n2. Return Book \n3. Browse Books \n4. Add or Remove Book \n5. Register New Student: \n6. Delete Student \n7. Exit");
            System.out.print("Please enter function number (1-7): ");
            int function = input.nextInt();

            if (function < 1 || function > 7) {
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
            else if (function == 1) {
                System.out.println("Borrow Book");
                
            }
            else if (function == 2) {
                System.out.println("Return Book");
                
            }
            else if (function == 3) {
                System.out.println("Browse Books");
                
            }
            else if (function == 4) {
                System.out.println("Add or Remove Book");
                new Adjust();
            }
            else if (function == 5) {
                System.out.println("Register New Student");
                
            }
            else if (function == 6) {
                System.out.println("Delete Student");
                
            }
            else {
                System.out.println("Have a great day!");
                break;
            }
        }

        System.out.print("Please enter your ID: ");
        int ID = input.nextInt();

        input.close();
    }
}