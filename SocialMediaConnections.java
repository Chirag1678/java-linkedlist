import java.util.ArrayList;
import java.util.Scanner;

// Node class to represent User
class UserNode {
    // Attributes
    int userId;
    String name;
    int age;
    ArrayList<Integer> friends; // List of Friend IDs
    UserNode next; // Pointer to next user

    // Constructor
    UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
        this.next = null;
    }
}

// Singly linked list to manage Friends
class FriendLinkedList {
    // Attribute
    private UserNode head;

    // Add user
    public void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if(head==null) {
            head = newUser;
            return;
        }
        UserNode temp = head;
        while(temp.next!=null) {
            temp = temp.next;
        }
        temp.next = newUser;
    }

    // Search user
    public UserNode searchUser(int userId) {
        UserNode temp = head;
        while(temp!=null) {
            if(temp.userId==userId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Add a connection between two users
    public void addFriend(int userId1, int userId2) {
        UserNode user1 = searchUser(userId1);
        UserNode user2 = searchUser(userId2);
        if (user1==null || user2==null) {
            System.out.println("User not found!");
            return;
        }
        if (!user1.friends.contains(userId2)) {
            user1.friends.add(userId2);
            user2.friends.add(userId1);
            System.out.println("Friend connection added successfully.");
        } else {
            System.out.println("Users are already friends.");
        }
    }

    // Remove a friend connection
    public void removeFriend(int userId1, int userId2) {
        UserNode user1 = searchUser(userId1);
        UserNode user2 = searchUser(userId2);
        if (user1==null || user2==null) {
            System.out.println("User not found!");
            return;
        }
        user1.friends.remove(Integer.valueOf(userId2));
        user2.friends.remove(Integer.valueOf(userId1));
        System.out.println("Friend connection removed successfully.");
    }

    // Find mutual friends between users
    public void mutualFriends(int userId1, int userId2) {
        UserNode user1 = searchUser(userId1);
        UserNode user2 = searchUser(userId2);
        if (user1==null || user2==null) {
            System.out.println("User not found!");
            return;
        }
        System.out.println("Mutual Friends between " + user1.name + " and " + user2.name + ":");
        for (int friendID:user1.friends) {
            if (user2.friends.contains(friendID)) {
                UserNode mutualFriend = searchUser(friendID);
                if (mutualFriend!=null) {
                    System.out.println(mutualFriend.name + " (UserID: " + mutualFriend.userId + ")");
                }
            }
        }
    }

    // Display friends of a user
    public void displayFriends(int userId) {
        UserNode user = searchUser(userId);
        if (user==null) {
            System.out.println("User not found!");
            return;
        }
        System.out.println("Friends of " + user.name + " (UserID: " + userId + "):");
        for (int friendID:user.friends) {
            UserNode friend = searchUser(friendID);
            if (friend!=null) {
                System.out.println(friend.name + " (UserID: " + friendID + ")");
            }
        }
    }

    // Search for a user by Name
    public void searchByName(String name) {
        UserNode temp = head;
        while(temp!=null) {
            if(temp.name.equalsIgnoreCase(name)) {
                System.out.println("User Found: ID=" + temp.userId + ", Name=" + temp.name + ", Age=" + temp.age);
                return;
            }
            temp = temp.next;
        }
        System.out.println("User not found!");
    }

    // Search user by ID
    public void searchByID(int userId) {
        UserNode temp = head;
        while(temp!=null) {
            if(temp.userId==userId) {
                System.out.println("User Found: ID=" + temp.userId + ", Name=" + temp.name + ", Age=" + temp.age);
                return;
            }
            temp = temp.next;
        }
        System.out.println("User not found!");
    }

    // Count number of friends of each user
    public void countFriends() {
        UserNode temp = head;
        while(temp!=null) {
            System.out.println(temp.name + " has " + temp.friends.size() + " friend(s).");
            temp = temp.next;
        }
    }
}

// Main class
public class SocialMediaConnections {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of Friends
        FriendLinkedList friends = new FriendLinkedList();

        // loop to take user input
        while(true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Display Friends");
            System.out.println("5. Find Mutual Friends");
            System.out.println("6. Search User By Name");
            System.out.println("7. Search User By ID");
            System.out.println("8. Count Friends");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userId1 = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name1 = input.nextLine();
                    System.out.print("Enter Age: ");
                    int age1 = input.nextInt();
                    friends.addUser(userId1, name1, age1);
                    break;

                case 2:
                    System.out.print("Enter User ID 1: ");
                    int userId21 = input.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int userId22 = input.nextInt();
                    friends.addFriend(userId21, userId22);
                    break;

                case 3:
                    System.out.print("Enter User ID 1: ");
                    int userId31 = input.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int userId32 = input.nextInt();
                    friends.removeFriend(userId31, userId32);
                    break;

                case 4:
                    System.out.print("Enter User ID: ");
                    int userId4 = input.nextInt();
                    friends.displayFriends(userId4);
                    break;

                case 5:
                    System.out.print("Enter User ID 1: ");
                    int userId51 = input.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int userId52 = input.nextInt();
                    friends.mutualFriends(userId51, userId52);
                    break;

                case 6:
                    input.nextLine(); // Consume newline
                    System.out.print("Enter User Name: ");
                    String name6 = input.nextLine();
                    friends.searchByName(name6);
                    break;

                case 7:
                    System.out.print("Enter User ID: ");
                    int userId7 = input.nextInt();
                    friends.searchByID(userId7);
                    break;

                case 8:
                    friends.countFriends();
                    break;

                case 9:
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
// 1. Add User
// 2. Add Friend Connection
// 3. Remove Friend Connection
// 4. Display Friends
// 5. Find Mutual Friends
// 6. Search User By Name
// 7. Search User By ID
// 8. Count Friends
// 9. Exit
// Enter your choice:  1
// Enter User ID: 1021
// Enter Name: Chirag
// Enter Age: 21

// 1. Add User
// 2. Add Friend Connection
// 3. Remove Friend Connection
// 4. Display Friends
// 5. Find Mutual Friends
// 6. Search User By Name
// 7. Search User By ID
// 8. Count Friends
// 9. Exit
// Enter your choice: 1
// Enter User ID: 1032
// Enter Name: Manan
// Enter Age: 21

// 1. Add User
// 2. Add Friend Connection
// 3. Remove Friend Connection
// 4. Display Friends
// 5. Find Mutual Friends
// 6. Search User By Name
// 7. Search User By ID
// 8. Count Friends
// 9. Exit
// Enter your choice: 2
// Enter User ID 1: 1021
// Enter User ID 2: 1032
// Friend connection added successfully.

// 1. Add User
// 2. Add Friend Connection
// 3. Remove Friend Connection
// 4. Display Friends
// 5. Find Mutual Friends
// 6. Search User By Name
// 7. Search User By ID
// 8. Count Friends
// 9. Exit
// Enter your choice: 4
// Enter User ID: 1021
// Friends of Chirag (UserID: 1021):
// Manan (UserID: 1032)

// 1. Add User
// 2. Add Friend Connection
// 3. Remove Friend Connection
// 4. Display Friends
// 5. Find Mutual Friends
// 6. Search User By Name
// 7. Search User By ID
// 8. Count Friends
// 9. Exit
// Enter your choice: 8
// Chirag has 1 friend(s).
// Manan has 1 friend(s).

// 1. Add User
// 2. Add Friend Connection
// 3. Remove Friend Connection
// 4. Display Friends
// 5. Find Mutual Friends
// 6. Search User By Name
// 7. Search User By ID
// 8. Count Friends
// 9. Exit
// Enter your choice: 9
// Exiting...