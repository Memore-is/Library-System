package libraryproject;

import java.util.ArrayList;
import java.util.Scanner;

public class Edit {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    
    public Edit() {
        while (true) {
            System.out.println("Options: \n1. Add Student \n2. Remove Student \n3. Go Back");
            System.out.print("Please enter function number (1-3): ");
            int function = input.nextInt();
            input.nextLine();

            if (function < 1 || function > 3) {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            } 
            else if (function == 1) {
                System.out.println("Add Student");
                addStudent();
            } 
            else if (function == 2) {
                System.out.println("Remove Student");
                removeStudent();
            }
            else {
                System.out.println("Going back to main menu...");
                break;
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();
        if (!name.isEmpty()) {
            students.add(new Student(name));
            System.out.println("Student added.");
        } else {
            System.out.println("Name cannot be empty.");
        }
    }

    private void removeStudent() {
        System.out.print("Enter student name to remove: ");
        String name = input.nextLine().trim();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                students.remove(student);
                System.out.println("Student removed.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
}