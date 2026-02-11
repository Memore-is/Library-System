package libraryproject;

import java.util.Scanner;

    public class StudentInfo { //Class for all student info 
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            // Ask for student's name
            System.out.print("Enter student's name: ");
            String name = sc.nextLine();

            // Ask for student's OSIS
            System.out.print("Enter Student OSIS Number: ");
            String studentOSIS = sc.nextLine();

            // Print confirmation
            System.out.println("Student Name: " + name);
            System.out.println("Student OSIS: " + studentOSIS);

            sc.close();
    }
}