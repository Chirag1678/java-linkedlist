import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Node class to represent a task
class TaskNode {
    // Attributes
    int taskId;
    String taskName;
    int priority;
    LocalDate dueDate;
    TaskNode next; // Pointer to next task

    // Constructor
    TaskNode(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.next = null;
    }
}

// Circular linked list to manage tasks
class TasksLinkedList {
    // Attributes
    private TaskNode head = null;
    private TaskNode tail = null;
    private TaskNode currentTask = null;

    // Add task at the beginning
    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newTask = new TaskNode(taskId, taskName, priority, dueDate);
        if(head==null) {
            head = tail = newTask;
            newTask.next = head; // Circular link
        }
        else {
            newTask.next = head;
            head = newTask;
            tail.next = head; // Circular link
        }
    }

    // Add task at the end
    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newTask = new TaskNode(taskId, taskName, priority, dueDate);
        if (head==null) {
            head = tail = newTask;
            newTask.next = head;
        }
        else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    // Add task at a specific position
    public void addAtPosition(int taskId, String taskName, int priority, String dueDate, int position) {
        if(position<=1) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }

        TaskNode newTask = new TaskNode(taskId, taskName, priority, dueDate);
        TaskNode temp = head;
        for(int i=1;i<position-1 && temp.next!=head;i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;

        if(temp==tail) {
            tail = newTask;
        }
    }

    // Remove task by Task ID
    public void deleteByID(int taskId) {
        if(head==null) {
            System.out.println("Task list is empty");
            return;
        }

        TaskNode temp = head, prev = null;
        do {
            if (temp.taskId==taskId) {
                if (temp==head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                if (temp==tail) {
                    tail = prev;
                }
                System.out.println("Task " + taskId + " removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp!=head);

        System.out.println("Task ID " + taskId + " not found.");
    }

    // View the current task and move to next task
    public void viewTask() {
        if(head==null) {
            System.out.println("Task list is empty");
            return;
        }
        if(currentTask==null) {
            currentTask=head;
        }
        System.out.println("Current Task Details: ");
        System.out.println("Task Name: " + currentTask.taskName);
        System.out.println("Task ID: " + currentTask.taskId);
        currentTask = currentTask.next;
    }

    // Display all tasks
    public void displayTasks() {
        if(head==null) {
            System.out.println("Task list is empty");
            return;
        }
        System.out.println("Tasks Details:");
        TaskNode temp = head;
        int i = 1;
        do {
            System.out.println("Details of Task: " + i);
            System.out.println("ID: " + temp.taskId);
            System.out.println("Name: " + temp.taskName);
            System.out.println("Priority: " + temp.priority);
            System.out.println("Due by: " + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(temp.dueDate));
            i++;
            temp = temp.next;
        } while(temp!=head);
    }

    // Search for task by priority
    public void searchByPriority(int priority) {
        if(head==null) {
            System.out.println("Task list is empty");
            return;
        }
        TaskNode temp = head;
        boolean found = false;
        do {
            if(temp.priority==priority) {
                System.out.println("Found: " + temp.taskName + " (ID: " + temp.taskId + ")");
                found = true;
            }
            temp = temp.next;
        } while(temp!=head);

        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }
}

// Main class
public class TaskScheduler {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of tasks
        TasksLinkedList tasks = new TasksLinkedList();

        // loop to take user input
        while (true) {
            System.out.println("\nTask Scheduler Menu:");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Specific Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch(choice) {
                case 1:
                    System.out.print("\nEnter Task ID: ");
                    int taskId1 = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter Task Name: ");
                    String taskName1 = input.nextLine();
                    System.out.print("Enter Priority of Task: ");
                    int priority1 = input.nextInt();
                    System.out.print("Enter Due Date (dd-mm-yyyy): ");
                    String dueDate1 = input.next();
                    tasks.addAtBeginning(taskId1, taskName1, priority1, dueDate1);
                    break;

                case 2:
                    System.out.print("\nEnter Task ID: ");
                    int taskId2 = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter Task Name: ");
                    String taskName2 = input.nextLine();
                    System.out.print("Enter Priority of Task: ");
                    int priority2 = input.nextInt();
                    System.out.print("Enter Due Date (dd-mm-yyyy): ");
                    String dueDate2 = input.next();
                    tasks.addAtEnd(taskId2, taskName2, priority2, dueDate2);
                    break;

                case 3:
                    System.out.print("\nEnter Task ID: ");
                    int taskId3 = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter Task Name: ");
                    String taskName3 = input.nextLine();
                    System.out.print("Enter Priority of Task: ");
                    int priority3 = input.nextInt();
                    System.out.print("Enter Due Date (dd-mm-yyyy): ");
                    String dueDate3 = input.next();
                    System.out.println("Enter Position to insert: ");
                    int position3 = input.nextInt();
                    tasks.addAtPosition(taskId3, taskName3, priority3, dueDate3, position3);
                    break;

                case 4:
                    System.out.print("\nEnter Task ID to remove: ");
                    tasks.deleteByID(input.nextInt());
                    break;

                case 5:
                    tasks.viewTask();
                    break;

                case 6:
                    tasks.displayTasks();
                    break;

                case 7:
                    System.out.print("\nEnter Priority: ");
                    tasks.searchByPriority(input.nextInt());
                    break;

                case 8:
                    System.out.println("\nExiting...");
                    input.close();
                    return;

                default:
                    System.out.println("\nInvalid choice! Try again.");
            }
        }
    }
}
// Sample Output ->
// Task Scheduler Menu:
// 1. Add Task at Beginning
// 2. Add Task at End
// 3. Add Task at Specific Position
// 4. Remove Task by ID
// 5. View Current Task
// 6. Display All Tasks
// 7. Search Task by Priority
// 8. Exit
// Enter choice: 1

// Enter Task ID: 101
// Enter Task Name: Complete linked list
// Enter Priority of Task: 1
// Enter Due Date (dd-mm-yyyy): 18-03-2025

// Task Scheduler Menu:
// 1. Add Task at Beginning
// 2. Add Task at End
// 3. Add Task at Specific Position
// 4. Remove Task by ID
// 5. View Current Task
// 6. Display All Tasks
// 7. Search Task by Priority
// 8. Exit
// Enter choice: 2

// Enter Task ID: 102
// Enter Task Name: Prepare for review
// Enter Priority of Task: 2
// Enter Due Date (dd-mm-yyyy): 19-03-2025

// Task Scheduler Menu:
// 1. Add Task at Beginning
// 2. Add Task at End
// 3. Add Task at Specific Position
// 4. Remove Task by ID
// 5. View Current Task
// 6. Display All Tasks
// 7. Search Task by Priority
// 8. Exit
// Enter choice: 6
// Tasks Details:
// Details of Task: 1
// ID: 101
// Name: Complete linked list
// Priority: 1
// Due by: 18-03-2025
// Details of Task: 2
// ID: 102
// Name: Prepare for review
// Priority: 2
// Due by: 19-03-2025

// Task Scheduler Menu:
// 1. Add Task at Beginning
// 2. Add Task at End
// 3. Add Task at Specific Position
// 4. Remove Task by ID
// 5. View Current Task
// 6. Display All Tasks
// 7. Search Task by Priority
// 8. Exit
// Enter choice: 8

// Exiting...