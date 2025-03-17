import java.util.Scanner;

// Node class represents a student record
class StudentNode {
    // Attributes
    int rollNumber;
    String name;
    int age;
    char grade;
    StudentNode next;

    // Constructor
    StudentNode(int rollNumber, String name, int age, char grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

// Singly linked list to manage Student Records
class StudentLinkedList {
    // Attribute -> head pointer
    private StudentNode head;

    // Add student at the beginning
    public void addAtStart(int rollNumber, String name, int age, char grade) {
        StudentNode newStudent = new StudentNode(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    // Add student at the end
    public void addAtEnd(int rollNumber, String name, int age, char grade) {
        StudentNode newStudent = new StudentNode(rollNumber, name, age, grade);
        if(head==null) {
            head = newStudent;
            return;
        }
        StudentNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    // Add student at a specific position
    public void addAtPosition(int rollNumber, String name, int age, char grade, int position) {
        StudentNode newStudent = new StudentNode(rollNumber, name, age, grade);
        if (position==1) {
            newStudent.next = head;
            head = newStudent;
            return;
        }

        StudentNode temp = head;
        for (int i=1; temp!=null && i<position-1; i++) {
            temp = temp.next;
        }

        if (temp==null) {
            System.out.println("Invalid position! Adding at the end.");
            addAtEnd(rollNumber, name, age, grade);
            return;
        }

        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    // Delete student by roll number
    public void deleteByRollNumber(int rollNumber) {
        if (head==null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.rollNumber==rollNumber) {
            head = head.next;
            System.out.println("Student with Roll No. " + rollNumber + " deleted.");
            return;
        }

        StudentNode temp = head, prev = null;
        while (temp!=null && temp.rollNumber!=rollNumber) {
            prev = temp;
            temp = temp.next;
        }

        if (temp==null) {
            System.out.println("Student not found.");
            return;
        }

        prev.next = temp.next;
        System.out.println("Student with Roll No. " + rollNumber + " deleted.");
    }

    // Search student by roll number
    public void searchByRollNumber(int rollNumber) {
        StudentNode temp = head;
        while (temp!=null) {
            if (temp.rollNumber == rollNumber) {
                System.out.println("Student Found:");
                System.out.println("Roll No: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No. " + rollNumber + " not found.");
    }

    // Update student's grade based on Roll Number
    public void updateGrade(int rollNumber, char newGrade) {
        StudentNode temp = head;
        while (temp!=null) {
            if (temp.rollNumber == rollNumber) {
                temp.grade = newGrade;
                System.out.println("Grade updated for Roll No. " + rollNumber);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No. " + rollNumber + " not found.");
    }

    // Display records of all Students
    public void displayDetails() {
        if(head==null) {
            System.out.println("No Student records found.");
            return;
        }
        StudentNode temp = head;
        System.out.println("Student Records:");
        int i = 1;
        while (temp!=null) {
            System.out.println("Details of Student " + i + ":");
            System.out.println("Roll No: " + temp.rollNumber);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Grade: " + temp.grade);
            i++;
            temp = temp.next;
        }
    }
}

// Main class
public class StudentSystem {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of Student
        StudentLinkedList studentList = new StudentLinkedList();

        // Loop to get user input
        while(true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Specific Position");
            System.out.println("4. Delete Student by Roll Number");
            System.out.println("5. Search Student by Roll Number");
            System.out.println("6. Update Student Grade");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("\nEnter Roll No: ");
                    int roll1 = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Name: ");
                    String name1 = input.nextLine();
                    System.out.print("Enter Age: ");
                    int age1 = input.nextInt();
                    System.out.print("Enter Grade: ");
                    char grade1 = input.next().charAt(0);
                    studentList.addAtStart(roll1, name1, age1, grade1);
                    break;

                case 2:
                    System.out.print("\nEnter Roll No: ");
                    int roll2 = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Name: ");
                    String name2 = input.nextLine();
                    System.out.print("Enter Age: ");
                    int age2 = input.nextInt();
                    System.out.print("Enter Grade: ");
                    char grade2 = input.next().charAt(0);
                    studentList.addAtEnd(roll2, name2, age2, grade2);
                    break;

                case 3:
                    System.out.print("\nEnter Roll No: ");
                    int roll3 = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Name: ");
                    String name3 = input.nextLine();
                    System.out.print("Enter Age: ");
                    int age3 = input.nextInt();
                    System.out.print("Enter Grade: ");
                    char grade3 = input.next().charAt(0);
                    System.out.print("Enter Position of Insertion: ");
                    int position3 = input.nextInt();
                    studentList.addAtPosition(roll3, name3, age3, grade3, position3);
                    break;

                case 4:
                    System.out.print("\nEnter Roll No to delete: ");
                    int roll4 = input.nextInt();
                    studentList.deleteByRollNumber(roll4);
                    break;

                case 5:
                    System.out.print("\nEnter Roll No to Search: ");
                    int roll5 = input.nextInt();
                    studentList.searchByRollNumber(roll5);
                    break;

                case 6:
                    System.out.print("\nEnter Roll No: ");
                    int roll6 = input.nextInt();
                    System.out.print("Enter new Grade: ");
                    char grade6 = input.next().charAt(0);
                    studentList.updateGrade(roll6, grade6);
                    break;

                case 7:
                    studentList.displayDetails();
                    break;

                case 8:
                    System.out.println("Exiting....");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid Choice! Choose from the menu(1-8)");
            }
        }
    }
}
// Sample Output ->
// Student Record Management System
// 1. Add Student at Beginning
// 2. Add Student at End
// 3. Add Student at Specific Position
// 4. Delete Student by Roll Number
// 5. Search Student by Roll Number
// 6. Update Student Grade
// 7. Display All Students
// 8. Exit
// Choose an option: 1

// Enter Roll No: 123
// Enter Name: Chirag
// Enter Age: 21
// Enter Grade: A

// Student Record Management System
// 1. Add Student at Beginning
// 2. Add Student at End
// 3. Add Student at Specific Position
// 4. Delete Student by Roll Number
// 5. Search Student by Roll Number
// 6. Update Student Grade
// 7. Display All Students
// 8. Exit
// Choose an option: 1

// Enter Roll No: 221
// Enter Name: Manan
// Enter Age: 21
// Enter Grade: B

// Student Record Management System
// 1. Add Student at Beginning
// 2. Add Student at End
// 3. Add Student at Specific Position
// 4. Delete Student by Roll Number
// 5. Search Student by Roll Number
// 6. Update Student Grade
// 7. Display All Students
// 8. Exit
// Choose an option: 7

// Student Records:
// Details of Student 1:
// Roll No: 221
// Name: Manan
// Age: 21
// Grade: B
// Details of Student 2:
// Roll No: 123
// Name: Chirag
// Age: 21
// Grade: A

// Student Record Management System
// 1. Add Student at Beginning
// 2. Add Student at End
// 3. Add Student at Specific Position
// 4. Delete Student by Roll Number
// 5. Search Student by Roll Number
// 6. Update Student Grade
// 7. Display All Students
// 8. Exit
// Choose an option: 8
// Exiting....