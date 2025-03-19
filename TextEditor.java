import java.util.Scanner;

// Node class to represent Text
class TextNode {
    //Attributes
    String text;
    TextNode prev, next; // Pointers for previous and next text

    // Constructor
    TextNode(String text) {
        this.text = text;
        this.prev = this.next = null;
    }
}

// Doubly linked list to manage text editor
class TextLinkedList {
    // Attributes
    private TextNode head;
    private TextNode tail;
    private TextNode current;
    private int size = 0;

    TextLinkedList() {
        head = tail = current = new TextNode(""); // Initial empty state
    }

    // Add a new text state
    public void addTextState(String newText) {
        TextNode newNode = new TextNode(newText);

        if (current!=null) {
            newNode.prev = current;
            current.next = newNode;
        }

        current = newNode;
        tail = current; // Redo history is cleared

        // Limit history to last 10 states
        int maxSize = 10;
        if (size< maxSize) {
            size++;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    // Undo function
    public void undo() {
        if (current.prev!=null) {
            current = current.prev;
            System.out.println("Undo done!");
        } else {
            System.out.println("No more undo available!");
        }
    }

    // Redo function
    public void redo() {
        if (current.next!=null) {
            current = current.next;
            System.out.println("Redo done!");
        } else {
            System.out.println("No more redo available!");
        }
    }

    // Display current state
    public void displayCurrentState() {
        System.out.println("Current Text: " + current.text);
    }
}

// Main clas
public class TextEditor {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of texts
        TextLinkedList editor = new TextLinkedList();

        // loop to take user input
        while(true) {
            System.out.println("\n1. Type Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current State");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newLine

            switch(choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String text = input.nextLine();
                    editor.addTextState(text);
                    break;

                case 2:
                    editor.undo();
                    break;

                case 3:
                    editor.redo();
                    break;

                case 4:
                    editor.displayCurrentState();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
// Sample Output ->
// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 1
// Enter text: Hello

// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 1
// Enter text: Hello World!

// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 4
// Current Text: Hello World!

// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 2
// Undo done!

// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 4
// Current Text: Hello

// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 3
// Redo done!

// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 4
// Current Text: Hello World!

// 1. Type Text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Enter your choice: 5
// Exiting...