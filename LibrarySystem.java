import java.util.Scanner;

// Node class to represent a book
class BookNode {
    // Attributes
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    BookNode next, prev; // pointers for next and previous books

    // Constructor
    BookNode(int bookId, String title, String author, String genre, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.next = this.prev = null;
    }
}

// Doubly linked list for Library Management
class LibraryLinkedList {
    // Attributes
    private BookNode head, tail;
    private int count = 0;

    // Add book at the Beginning
    public void addAtBeginning(int bookId, String title, String author, String genre, boolean isAvailable) {
        BookNode newBook = new BookNode(bookId, title, author, genre, isAvailable);
        if(head==null) {
            head = tail = newBook; // First book in list
        }
        else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        count++;
        System.out.println("Book added successfully.");
    }

    // Add book at the end
    public void addAtEnd(int bookId, String title, String author, String genre, boolean isAvailable) {
        BookNode newBook = new BookNode(bookId, title, author, genre, isAvailable);
        if(head==null) {
            head = tail = newBook;
        }
        else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        count++;
        System.out.println("Book added successfully.");
    }

    // Add book at a specific position
    public void addAtPosition(int bookId, String title, String author, String genre, boolean isAvailable, int position) {
        if(position<=1) {
            addAtBeginning(bookId, title, author, genre, isAvailable);
            return;
        }

        BookNode newBook = new BookNode(bookId, title, author, genre, isAvailable);
        BookNode temp = head;
        for(int i=1;i<position-1 && temp!=null;i++) {
            temp = temp.next;
        }

        if(temp==null || temp.next==null){
            System.out.println("Invalid position. Adding at the end.");
            addAtEnd(bookId, title, author, genre, isAvailable);
            return;
        }

        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
        count++;
        System.out.println("Book added successfully.");
    }

    // Delete a book by ID
    public void deleteById(int bookId) {
        if(head==null) {
            System.out.println("Library is empty.");
            return;
        }

        BookNode temp = head;

        // If book is at the head
        if(head.bookId==bookId) {
            head = head.next;
            if(head!=null) head.prev = null;
            else tail=null;
            System.out.println("Book with ID: " + bookId + " deleted.");
            count--;
            return;
        }

        while(temp!=null && temp.bookId!=bookId) {
            temp = temp.next;
        }

        if(temp==null) {
            System.out.println("Book with ID: " + bookId + " not found.");
            return;
        }

        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp == tail) tail = temp.prev;

        count--;
        System.out.println("Book with ID: " + bookId + " deleted.");
    }

    // Search book by title
    public void searchByTitle(String title) {
        BookNode temp = head;
        boolean found = false;

        while(temp!=null) {
            if(temp.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + temp.title + "(ID: " + temp.bookId + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Available: " + (temp.isAvailable ? "Yes" : "No") + ")");
                found = true;
            }
            temp = temp.next;
        }

        if(!found) {
            System.out.println("No Book found with title: " + title);
        }
    }

    // search book by Author
    public void searchByAuthor(String author) {
        BookNode temp = head;
        boolean found = false;

        while(temp!=null) {
            if(temp.author.equalsIgnoreCase(author)) {
                System.out.println("Found: " + temp.title + "(ID: " + temp.bookId + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Available: " + (temp.isAvailable ? "Yes" : "No") + ")");
                found = true;
            }
            temp = temp.next;
        }

        if(!found) {
            System.out.println("No Book found of author: " + author);
        }
    }

    // Update book's availability by ID
    public void updateAvailability(int bookId, boolean availability) {
        BookNode temp = head;

        while(temp!=null) {
            if(temp.bookId==bookId) {
                temp.isAvailable = availability;
                System.out.println("Updated availability of " + temp.title + " to " + (temp.isAvailable? "Yes" : "No"));
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID: " + bookId + " not found.");
    }

    // Display all books forward
    public void displayBooksForward() {
        if(head==null) {
            System.out.println("No Books Available.");
            return;
        }
        System.out.println("Book List (Forward Order):");
        BookNode temp = head;
        int i = 1;
        while(temp!=null) {
            System.out.println("Details of Book: " + i);
            System.out.println("ID: " + temp.bookId);
            System.out.println("Title: " + temp.title);
            System.out.println("Author: " + temp.author);
            System.out.println("Genre: " + temp.genre);
            System.out.println("Available: " + (temp.isAvailable ? "Yes" : "No"));
            i++;
            temp = temp.next;
        }
    }

    // Display all books reverse
    public void displayBooksReverse() {
        if(tail==null) {
            System.out.println("No books available");
            return;
        }
        System.out.println("Book List (Reverse Order): ");
        BookNode temp = tail;
        int i = 1;
        while(temp!=null) {
            System.out.println("Details of Book: " + i);
            System.out.println("ID: " + temp.bookId);
            System.out.println("Title: " + temp.title);
            System.out.println("Author: " + temp.author);
            System.out.println("Genre: " + temp.genre);
            System.out.println("Available: " + (temp.isAvailable ? "Yes" : "No"));
            i++;
            temp = temp.prev;
        }
    }

    // Count number of books in library
    public void countBooks() {
        System.out.println("Total number of books: " + count);
    }
}

// Main class
public class LibrarySystem {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of books
        LibraryLinkedList library = new LibraryLinkedList();

        // loop to take user input
        while(true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at a Position");
            System.out.println("4. Remove Book");
            System.out.println("5. Search Book By Title");
            System.out.println("6. Search Book By Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display Books (Forward)");
            System.out.println("9. Display Books (Reverse)");
            System.out.println("10. Count Books");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch(choice) {
                case 1:
                    System.out.print("\nEnter Book ID: ");
                    int id1 = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Title: ");
                    String title1 = input.nextLine();
                    System.out.print("Enter Author: ");
                    String author1 = input.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre1 = input.nextLine();
                    System.out.print("Is Available (true/false): ");
                    boolean available1 = input.nextBoolean();
                    library.addAtBeginning(id1, title1, author1, genre1, available1);
                    break;

                case 2:
                    System.out.print("\nEnter Book ID: ");
                    int id2 = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Title: ");
                    String title2 = input.nextLine();
                    System.out.print("Enter Author: ");
                    String author2 = input.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre2 = input.nextLine();
                    System.out.print("Is Available (true/false): ");
                    boolean available2 = input.nextBoolean();
                    library.addAtEnd(id2, title2, author2, genre2, available2);
                    break;

                case 3:
                    System.out.print("\nEnter Book ID: ");
                    int id3 = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Title: ");
                    String title3 = input.nextLine();
                    System.out.print("Enter Author: ");
                    String author3 = input.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre3 = input.nextLine();
                    System.out.print("Is Available (true/false): ");
                    boolean available3 = input.nextBoolean();
                    System.out.print("Enter Position to insert: ");
                    int position3 = input.nextInt();
                    library.addAtPosition(id3, title3, author3, genre3, available3, position3);
                    break;

                case 4:
                    System.out.print("Enter Book ID to Remove: ");
                    int bookId4 = input.nextInt();
                    library.deleteById(bookId4);
                    break;

                case 5:
                    System.out.print("Enter Book Title: ");
                    String title5 = input.nextLine();
                    library.searchByTitle(title5);
                    break;

                case 6:
                    System.out.print("Enter Book Author: ");
                    String author6 = input.nextLine();
                    library.searchByAuthor(author6);
                    break;

                case 7:
                    System.out.print("Enter Book ID to Update: ");
                    int updateID = input.nextInt();
                    System.out.print("Is Available (true/false): ");
                    boolean updateAvail = input.nextBoolean();
                    library.updateAvailability(updateID, updateAvail);
                    break;

                case 8:
                    library.displayBooksForward();
                    break;

                case 9:
                    library.displayBooksReverse();
                    break;

                case 10:
                    library.countBooks();
                    break;

                case 11:
                    System.out.println("Exiting...");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
// Sample Ouput ->
// Library Management System
// 1. Add Book at Beginning
// 2. Add Book at End
// 3. Add Book at a Position
// 4. Remove Book
// 5. Search Book By Title
// 6. Search Book By Author
// 7. Update Availability
// 8. Display Books (Forward)
// 9. Display Books (Reverse)
// 10. Count Books
// 11. Exit
// Enter choice: 1

// Enter Book ID: 101
// Enter Title: Harry Potter 1
// Enter Author: JK Rowling
// Enter Genre: Fiction
// Is Available (true/false): true
// Book added successfully.

// Library Management System
// 1. Add Book at Beginning
// 2. Add Book at End
// 3. Add Book at a Position
// 4. Remove Book
// 5. Search Book By Title
// 6. Search Book By Author
// 7. Update Availability
// 8. Display Books (Forward)
// 9. Display Books (Reverse)
// 10. Count Books
// 11. Exit
// Enter choice: 1

// Enter Book ID: 102
// Enter Title: Harry Potter 2
// Enter Author: JK Rowling
// Enter Genre: Fiction/Adventure
// Is Available (true/false): false
// Book added successfully.

// Library Management System
// 1. Add Book at Beginning
// 2. Add Book at End
// 3. Add Book at a Position
// 4. Remove Book
// 5. Search Book By Title
// 6. Search Book By Author
// 7. Update Availability
// 8. Display Books (Forward)
// 9. Display Books (Reverse)
// 10. Count Books
// 11. Exit
// Enter choice: 8
// Book List (Forward Order):
// Details of Book: 1
// ID: 102
// Title: Harry Potter 2
// Author: JK Rowling
// Genre: Fiction/Adventure
// Available: No
// Details of Book: 2
// ID: 101
// Title: Harry Potter 1
// Author: JK Rowling
// Genre: Fiction
// Available: Yes

// Library Management System
// 1. Add Book at Beginning
// 2. Add Book at End
// 3. Add Book at a Position
// 4. Remove Book
// 5. Search Book By Title
// 6. Search Book By Author
// 7. Update Availability
// 8. Display Books (Forward)
// 9. Display Books (Reverse)
// 10. Count Books
// 11. Exit
// Enter choice: 10
// Total number of books: 2

// Library Management System
// 1. Add Book at Beginning
// 2. Add Book at End
// 3. Add Book at a Position
// 4. Remove Book
// 5. Search Book By Title
// 6. Search Book By Author
// 7. Update Availability
// 8. Display Books (Forward)
// 9. Display Books (Reverse)
// 10. Count Books
// 11. Exit
// Enter choice: 11
// Exiting...