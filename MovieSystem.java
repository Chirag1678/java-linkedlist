import java.util.Scanner;

// Node class to represent a movie
class MovieNode {
    // Attributes
    String title;
    String director;
    int yearOfRelease;
    double rating;
    MovieNode next, prev; // Pointers for doubly linked list

    // Constructor
    MovieNode(String title, String director, int yearOfRelease, double rating) {
        this.title = title;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

// Doubly linked list for Movie Management
class MovieLinkedList {
    // Attributes -> head and tail pointers
    MovieNode head, tail; // Head points to first movie, tail to the last movie

    // Add movie at beginning
    public void addAtBeginning(String title, String director, int year, double rating) {
        MovieNode newMovie = new MovieNode(title, director, year, rating);
        if (head==null) {
            head = tail = newMovie; // First movie in the list
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
        System.out.println("Movie added successfully");
    }

    // Add movie at end
    public void addAtEnd(String title, String director, int year, double rating) {
        MovieNode newMovie = new MovieNode(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
        System.out.println("Movie added successfully");
    }

    // Add movie at specific position
    public void addAtPosition(String title, String director, int year, double rating, int position) {
        if (position <= 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        MovieNode newMovie = new MovieNode(title, director, year, rating);
        MovieNode temp = head;
        for(int i=1;i<position-1 && temp!=null; i++) {
            temp = temp.next;
        }

        if(temp==null || temp.next==null){
            addAtEnd(title, director, year, rating);
            return;
        }

        newMovie.next = temp.next;
        newMovie.prev = temp;
        temp.next.prev = newMovie;
        temp.next = newMovie;
        System.out.println("Movie added successfully");
    }

    // Delete a movie by title
    public void deleteByTitle(String title) {
        if(head==null) {
            System.out.println("Movie list is empty");
            return;
        }

        MovieNode temp = head;

        // If movie is at the head
        if(head.title.equalsIgnoreCase(title)) {
            head = head.next;
            if(head!=null) head.prev = null;
            else tail=null;
            System.out.println("Movie " + title + " deleted.");
            return;
        }

        // Traverse the list to find the movie
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Movie '" + title + "' not found.");
            return;
        }

        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp == tail) tail = temp.prev; // If last movie is deleted

        System.out.println("Movie '" + title + "' deleted.");
    }

    // Search movie by director
    public void searchByDirector(String director) {
        MovieNode temp = head;
        boolean found = false;

        while(temp!=null) {
            if(temp.director.equalsIgnoreCase(director)) {
                System.out.println("Found: " + temp.title + " (Year: " + temp.yearOfRelease + ", Rating: " + temp.rating + ")");
                found = true;
            }
            temp = temp.next;
        }
        if(!found) {
            System.out.println("No movies found by Director: " + director);
        }
    }

    // Search movie by rating
    public void searchByRating(double rating) {
        MovieNode temp = head;
        boolean found = false;

        while(temp!=null) {
            if(temp.rating==rating) {
                System.out.println("Found: " + temp.title + " (Directed by: " + temp.director + ", Year: " + temp.yearOfRelease + ")");
                found = true;
            }
            temp = temp.next;
        }
        if(!found) {
            System.out.println("No movies found with Rating: " + rating);
        }
    }

    // Update movie rating by title
    public void updateRating(String title, double newRating) {
        MovieNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Updated rating of '" + title + "' to " + newRating);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie '" + title + "' not found.");
    }

    // Display all movies forward
    public void displayMoviesForward() {
        if (head == null) {
            System.out.println("No movies available.");
            return;
        }
        System.out.println("Movie List (Forward Order):");
        MovieNode temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.yearOfRelease + " | Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    // Display all movies reverse
    public void displayMoviesReverse() {
        if (tail == null) {
            System.out.println("No movies available.");
            return;
        }
        System.out.println("Movie List (Reverse Order):");
        MovieNode temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.yearOfRelease + " | Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
}

// Main class
public class MovieSystem {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of movies
        MovieLinkedList movieList = new MovieLinkedList();

        // loop to take user input
        while (true) {
            System.out.println("\nMovie Management System");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Specific Position");
            System.out.println("4. Delete Movie by Title");
            System.out.println("5. Search Movie by Director");
            System.out.println("6. Search Movie by Rating");
            System.out.println("7. Update Movie Rating");
            System.out.println("8. Display All Movies (Forward)");
            System.out.println("9. Display All Movies (Reverse)");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("\nEnter Title of Movie: ");
                    String title1 = input.nextLine();
                    System.out.print("Enter director of movie: ");
                    String director1 = input.nextLine();
                    System.out.print("Enter year of release: ");
                    int year1 = input.nextInt();
                    System.out.print("Enter ratings of movie: ");
                    double rating1 = input.nextDouble();
                    movieList.addAtBeginning(title1, director1, year1, rating1);
                    break;

                case 2:
                    System.out.print("\nEnter Title of Movie: ");
                    String title2 = input.nextLine();
                    System.out.print("Enter director of movie: ");
                    String director2 = input.nextLine();
                    System.out.print("Enter year of release: ");
                    int year2 = input.nextInt();
                    System.out.print("Enter ratings of movie: ");
                    double rating2 = input.nextDouble();
                    movieList.addAtEnd(title2, director2, year2, rating2);
                    break;

                case 3:
                    System.out.print("\nEnter Title of Movie: ");
                    String title3 = input.nextLine();
                    System.out.print("Enter director of movie: ");
                    String director3 = input.nextLine();
                    System.out.print("Enter year of release: ");
                    int year3 = input.nextInt();
                    System.out.print("Enter ratings of movie: ");
                    double rating3 = input.nextDouble();
                    System.out.print("Enter position to insert: ");
                    int position3 = input.nextInt();
                    movieList.addAtPosition(title3, director3, year3, rating3, position3);
                    break;

                case 4:
                    System.out.print("\nEnter Movie Title to delete: ");
                    movieList.deleteByTitle(input.nextLine());
                    break;
                case 5:
                    System.out.print("\nEnter Director Name: ");
                    movieList.searchByDirector(input.nextLine());
                    break;
                case 6:
                    System.out.print("\nEnter Rating: ");
                    movieList.searchByRating(input.nextDouble());
                    break;
                case 7:
                    System.out.print("\nEnter Movie Title: ");
                    String title7 = input.nextLine();
                    System.out.print("Enter new rating: ");
                    double rating7 = input.nextDouble();
                    movieList.updateRating(title7, rating7);
                    break;
                case 8:
                    movieList.displayMoviesForward();
                    break;
                case 9:
                    movieList.displayMoviesReverse();
                    break;
                case 10:
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
// Movie Management System
// 1. Add Movie at Beginning
// 2. Add Movie at End
// 3. Add Movie at Specific Position
// 4. Delete Movie by Title
// 5. Search Movie by Director
// 6. Search Movie by Rating
// 7. Update Movie Rating
// 8. Display All Movies (Forward)
// 9. Display All Movies (Reverse)
// 10. Exit
// Enter choice: 1

// Enter Title of Movie: Harry Potter
// Enter director of movie: JK Rowling
// Enter year of release: 2000
// Enter ratings of movie: 4.85

// Movie Management System
// 1. Add Movie at Beginning
// 2. Add Movie at End
// 3. Add Movie at Specific Position
// 4. Delete Movie by Title
// 5. Search Movie by Director
// 6. Search Movie by Rating
// 7. Update Movie Rating
// 8. Display All Movies (Forward)
// 9. Display All Movies (Reverse)
// 10. Exit
// Enter choice:  8
// Movie List (Forward Order):
// Harry Potter | JK Rowling | 2000 | Rating: 4.85

// Movie Management System
// 1. Add Movie at Beginning
// 2. Add Movie at End
// 3. Add Movie at Specific Position
// 4. Delete Movie by Title
// 5. Search Movie by Director
// 6. Search Movie by Rating
// 7. Update Movie Rating
// 8. Display All Movies (Forward)
// 9. Display All Movies (Reverse)
// 10. Exit
// Enter choice: 2

// Enter Title of Movie: Harry potter 2
// Enter director of movie: JK Rowling 
// Enter year of release: 2001
// Enter ratings of movie: 4.56

// Movie Management System
// 1. Add Movie at Beginning
// 2. Add Movie at End
// 3. Add Movie at Specific Position
// 4. Delete Movie by Title
// 5. Search Movie by Director
// 6. Search Movie by Rating
// 7. Update Movie Rating
// 8. Display All Movies (Forward)
// 9. Display All Movies (Reverse)
// 10. Exit
// Enter choice: 9
// Movie List (Reverse Order):
// Harry potter 2 | JK Rowling  | 2001 | Rating: 4.56
// Harry Potter | JK Rowling | 2000 | Rating: 4.85

// Movie Management System
// 1. Add Movie at Beginning
// 2. Add Movie at End
// 3. Add Movie at Specific Position
// 4. Delete Movie by Title
// 5. Search Movie by Director
// 6. Search Movie by Rating
// 7. Update Movie Rating
// 8. Display All Movies (Forward)
// 9. Display All Movies (Reverse)
// 10. Exit
// Enter choice: 10
// Exiting...