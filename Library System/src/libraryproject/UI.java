package libraryproject;

import java.io.*;
import java.util.*;

public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Library!");

        while (true) {
            System.out.println("Options: \n1. Borrow Book \n2. Return Book \n3. Browse \n4. Add or Remove Book \n5. Edit Students \n6. Exit");
            System.out.print("Please enter function number (1-6): ");
            int function = input.nextInt();

            if (function < 1 || function > 6) {
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
            else if (function == 1) {
                System.out.println("Borrow Book");
                new Borrow();
            }
            else if (function == 2) {
                System.out.println("Return Book");
                new Return();
            }
            else if (function == 3) {
                System.out.println("Browse");
                new Browse();
            }
            else if (function == 4) {
                System.out.println("Add or Remove Book");
                new Adjust();
            }
            else if (function == 5) {
                System.out.println("Edit Students");
                new Edit();
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