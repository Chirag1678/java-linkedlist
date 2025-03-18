import java.util.Scanner;

// Node class to represent a process
class ProcessNode {
    // Attributes
    int processId;
    int burstTime;
    int priority;
    ProcessNode next; // Pointer to next process

    // Constructor
    ProcessNode(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

// Circular linked list to manage processes
class ProcessLinkedList {
    // Attributes
    private ProcessNode head = null;
    private ProcessNode tail = null;
    private int timeQuantum;

    // Constructor
    ProcessLinkedList(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    // Add process at the end
    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newProcess = new ProcessNode(processId, burstTime, priority);
        if (head==null) {
            head = tail = newProcess;
            newProcess.next = head; // Circular link
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
    }

    // Remove process by Process ID
    public void removeProcess(int processId) {
        if (head==null) {
            System.out.println("No processes to remove.");
            return;
        }

        ProcessNode temp = head, prev = null;
        do {
            if (temp.processId==processId) {
                if (temp==head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                if (temp==tail) {
                    tail = prev;
                }
                System.out.println("Process " + processId + " completed and removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp!=head);

        System.out.println("Process ID " + processId + " not found.");
    }

    // Simulate round-robin scheduling
    public void executeProcesses() {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        System.out.println("Executing processes in Round Robin manner with time quantum: " + timeQuantum);
        ProcessNode current = head;
        int totalWaitingTime = 0, totalTurnAroundTime = 0, completedProcesses = 0;

        while (head!=null) {
            if (current.burstTime > timeQuantum) {
                System.out.println("Process " + current.processId + " executed for " + timeQuantum + " units.");
                current.burstTime -= timeQuantum;
            } else {
                System.out.println("Process " + current.processId + " executed for " + current.burstTime + " units and completed.");
                totalWaitingTime += (completedProcesses * timeQuantum);
                totalTurnAroundTime += totalWaitingTime + current.burstTime;
                completedProcesses++;
                removeProcess(current.processId);
                current = head;
                if (head == null) break;
            }
            current = current.next;
        }

        // Display average waiting and turnaround time
        if (completedProcesses > 0) {
            System.out.println("Average Waiting Time: " + (double) totalWaitingTime / completedProcesses);
            System.out.println("Average Turnaround Time: " + (double) totalTurnAroundTime / completedProcesses);
        }
    }

    // Display list of processes
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        System.out.println("Processes in the queue:");
        ProcessNode temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

// Main class
public class RoundRobin {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Get time quantum
        System.out.print("Enter Time Quantum: ");
        int timeQuantum = input.nextInt();

        // Create a process queue
        ProcessLinkedList processQueue = new ProcessLinkedList(timeQuantum);

        // Menu loop
        while (true) {
            System.out.println("\nRound Robin Scheduler Menu:");
            System.out.println("1. Add Process");
            System.out.println("2. Remove Process");
            System.out.println("3. Execute Processes");
            System.out.println("4. Display Processes");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Process ID: ");
                    int processId = input.nextInt();
                    System.out.print("Enter Burst Time: ");
                    int burstTime = input.nextInt();
                    System.out.print("Enter Priority: ");
                    int priority = input.nextInt();
                    processQueue.addProcess(processId, burstTime, priority);
                    break;

                case 2:
                    System.out.print("\nEnter Process ID to remove: ");
                    int removeId = input.nextInt();
                    processQueue.removeProcess(removeId);
                    break;

                case 3:
                    processQueue.executeProcesses();
                    break;

                case 4:
                    processQueue.displayProcesses();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
// Sample Output ->
// Enter Time Quantum: 5

// Round Robin Scheduler Menu:
// 1. Add Process
// 2. Remove Process
// 3. Execute Processes
// 4. Display Processes
// 5. Exit
// Enter choice: 
// 1

// Enter Process ID: 101
// Enter Burst Time: 4
// Enter Priority: 2

// Round Robin Scheduler Menu:
// 1. Add Process
// 2. Remove Process
// 3. Execute Processes
// 4. Display Processes
// 5. Exit
// Enter choice: 1

// Enter Process ID: 102
// Enter Burst Time: 6
// Enter Priority: 1

// Round Robin Scheduler Menu:
// 1. Add Process
// 2. Remove Process
// 3. Execute Processes
// 4. Display Processes
// 5. Exit
// Enter choice: 4
// Processes in the queue:
// Process ID: 101, Burst Time: 4, Priority: 2
// Process ID: 102, Burst Time: 6, Priority: 1

// Round Robin Scheduler Menu:
// 1. Add Process
// 2. Remove Process
// 3. Execute Processes
// 4. Display Processes
// 5. Exit
// Enter choice: 5
// Exiting...