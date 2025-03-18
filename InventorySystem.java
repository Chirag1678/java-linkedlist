import java.util.Scanner;

// Node class to represent an item
class ItemNode {
    // Attributes
    String itemName;
    int itemId;
    int quantity;
    double price;
    ItemNode next; // Pointer to next item

    // Constructor
    ItemNode(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

// Singly linked list to manage inventory
class ItemLinkedList {
    // Attributes
    private ItemNode head;

    // Add Item at the Beginning
    public void addAtBeginning(String itemName, int itemId, int quantity, double price) {
        ItemNode newItem = new ItemNode(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Add Item at the End
    public void addAtEnd(String itemName, int itemId, int quantity, double price) {
        ItemNode newItem = new ItemNode(itemName, itemId, quantity, price);
        if(head==null) {
            head = newItem;
            return;
        }
        ItemNode temp = head;
        while(temp.next!=null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    // Add item at a specific Position
    public void addAtPosition(String itemName, int itemId, int quantity, double price, int position) {
        if(position<=1) {
            addAtBeginning(itemName, itemId, quantity, price);
            return;
        }

        ItemNode newItem = new ItemNode(itemName, itemId, quantity, price);
        ItemNode temp = head;
        for (int i=1; temp!=null && i<position-1; i++) {
            temp = temp.next;
        }

        if(temp==null) {
            System.out.println("Invalid Position! Adding at the end.");
            addAtEnd(itemName, itemId, quantity, price);
            return;
        }

        newItem.next = temp.next;
        temp.next = newItem;
    }

    // Delete item by Item ID
    public void deleteById(int itemId) {
        if(head==null) {
            System.out.println("Item list is empty.");
            return;
        }

        if (head.itemId == itemId) {
            head = head.next;
            System.out.println("Item with ID: " + itemId + " deleted.");
            return;
        }

        ItemNode temp = head, prev = null;
        while(temp!=null &&  temp.itemId!=itemId) {
            prev = temp;
            temp = temp.next;
        }

        if(temp==null) {
            System.out.println("Item with ID: " + itemId + " not found.");
            return;
        }

        prev.next = temp.next;
        System.out.println("Item with ID: " + itemId + " deleted.");
    }

    // Update quantity based on Item ID
    public void updateQuantity(int itemId, int newQuantity) {
        ItemNode temp = head;
        while(temp!=null) {
            if(temp.itemId==itemId) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated for item with ID: " + itemId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID: " + itemId + " not found.");
    }

    // Search item by ID
    public void searchById(int itemId) {
        ItemNode temp = head;
        boolean found = false;

        while(temp!=null) {
            if(temp.itemId==itemId) {
                System.out.println("Found: " + temp.itemName + "(ID: " + temp.itemId + ", price: " + temp.price + ", quantity: " + temp.quantity + ")");
                found = true;
            }
            temp = temp.next;
        }

        if(!found) {
            System.out.println("No item found with ID: " + itemId);
        }
    }

    // Search item by Name
    public void searchByName(String itemName) {
        ItemNode temp = head;
        boolean found = false;

        while(temp!=null) {
            if(temp.itemName==itemName) {
                System.out.println("Found: " + temp.itemName + "(ID: " + temp.itemId + ", price: " + temp.price + ", quantity: " + temp.quantity + ")");
                return;
            }
            temp = temp.next;
        }

        if(!found){
            System.out.println("No item found with Name: " + itemName);
        }
    }

    // Calculate total value of inventory
    public void totalValue() {
        double totalValue = 0;
        ItemNode temp = head;

        while(temp!=null) {
            totalValue += temp.price * temp.quantity;
            temp = temp.next;
        }

        System.out.printf("Total Value of Inventory: %.2f\n", totalValue);
    }

    // Display all items
    public void displayItems() {
        if(head==null) {
            System.out.println("No items available");
            return;
        }

        ItemNode temp = head;
        int i = 1;
        while(temp!=null) {
            System.out.println("Details of Item: " + i);
            System.out.println("Item: " + temp.itemName);
            System.out.println("ID: " + temp.itemId);
            System.out.println("Quantity: " + temp.quantity);
            System.out.println("Price: " + temp.price);
            i++;
            temp = temp.next;
        }
    }

    // Sort inventory by name
    public void sortByName() {
        if(head==null) {
            System.out.println("No items available");
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            ItemNode temp = head;
            while(temp.next!=null) {
                if(temp.itemName.compareToIgnoreCase(temp.next.itemName)>0) {
                    swap(temp, temp.next);
                    swapped = true;
                }
                temp = temp.next;
            }
        } while(swapped);

        System.out.println("Items sorted according to item name.");
    }

    // Helper method to swap nodes
    public void swap(ItemNode nodeA, ItemNode nodeB) {
        String tempName = nodeA.itemName;
        int tempId = nodeA.itemId, tempQty = nodeA.quantity;
        double tempPrice = nodeA.price;

        nodeA.itemName = nodeB.itemName;
        nodeA.itemId = nodeB.itemId;
        nodeA.quantity = nodeB.quantity;
        nodeA.price = nodeB.price;

        nodeB.itemName = tempName;
        nodeB.itemId = tempId;
        nodeB.quantity = tempQty;
        nodeB.price = tempPrice;
    }
}

// Main class
public class InventorySystem {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of items
        ItemLinkedList items = new ItemLinkedList();

        // loop to take user input
        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Specific Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity by Item ID");
            System.out.println("6. Search Item by ID");
            System.out.println("7. Search Item by Name");
            System.out.println("8. Calculate Total Inventory Value");
            System.out.println("9. Sort Inventory by name");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch(choice) {
                case 1:
                    System.out.print("\nEnter Item Name: ");
                    String itemName1 = input.nextLine();
                    System.out.print("Enter Item ID: ");
                    int itemId1 = input.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity1 = input.nextInt();
                    System.out.print("Enter Price of Item: ");
                    double price1 = input.nextDouble();
                    items.addAtBeginning(itemName1, itemId1, quantity1, price1);
                    break;

                case 2:
                    System.out.print("\nEnter Item Name: ");
                    String itemName2 = input.nextLine();
                    System.out.print("Enter Item ID: ");
                    int itemId2 = input.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity2 = input.nextInt();
                    System.out.print("Enter Price of Item: ");
                    double price2 = input.nextDouble();
                    items.addAtEnd(itemName2, itemId2, quantity2, price2);
                    break;

                case 3:
                    System.out.print("\nEnter Item Name: ");
                    String itemName3 = input.nextLine();
                    System.out.print("Enter Item ID: ");
                    int itemId3 = input.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity3 = input.nextInt();
                    System.out.print("Enter Price of Item: ");
                    double price3 = input.nextDouble();
                    System.out.print("Enter Position to insert: ");
                    int position3 = input.nextInt();
                    items.addAtPosition(itemName3, itemId3, quantity3, price3, position3);
                    break;

                case 4:
                    System.out.print("\nEnter Item ID: ");
                    items.deleteById(input.nextInt());
                    break;

                case 5:
                    System.out.print("\nEnter Item ID: ");
                    int itemId5 = input.nextInt();
                    System.out.print("Enter new Quantity: ");
                    int quantity5 = input.nextInt();
                    items.updateQuantity(itemId5, quantity5);
                    break;

                case 6:
                    System.out.print("\nEnter Item ID: ");
                    int itemId6 = input.nextInt();
                    items.searchById(itemId6);
                    break;

                case 7:
                    System.out.println("\nEnter Item Name: ");
                    String itemName7 = input.nextLine();
                    items.searchByName(itemName7);
                    break;

                case 8:
                    items.totalValue();
                    break;

                case 9:
                    items.sortByName();
                    break;

                case 10:
                    System.out.println("Exiting....");
                    input.close();
                    break;

                default:
                    System.out.println("invalid Choice. Try again.");
            }
        }
    }
}
// Sample Output ->
// Inventory Management System
// 1. Add Item at Beginning
// 2. Add Item at End
// 3. Add Item at Specific Position
// 4. Remove Item by ID
// 5. Update Quantity by Item ID
// 6. Search Item by ID
// 7. Search Item by Name
// 8. Calculate Total Inventory Value
// 9. Sort Inventory by name
// 10. Exit
// Enter choice: 1

// Enter Item Name: Laptop
// Enter Item ID: 1001
// Enter Quantity: 5
// Enter Price of Item: 25000

// Inventory Management System
// 1. Add Item at Beginning
// 2. Add Item at End
// 3. Add Item at Specific Position
// 4. Remove Item by ID
// 5. Update Quantity by Item ID
// 6. Search Item by ID
// 7. Search Item by Name
// 8. Calculate Total Inventory Value
// 9. Sort Inventory by name
// 10. Exit
// Enter choice: 2

// Enter Item Name: Mobile
// Enter Item ID: 123
// Enter Quantity: 4
// Enter Price of Item: 10500

// Inventory Management System
// 1. Add Item at Beginning
// 2. Add Item at End
// 3. Add Item at Specific Position
// 4. Remove Item by ID
// 5. Update Quantity by Item ID
// 6. Search Item by ID
// 7. Search Item by Name
// 8. Calculate Total Inventory Value
// 9. Sort Inventory by name
// 10. Exit
// Enter choice: 8
// Total Value of Inventory: 167000.00

// Inventory Management System
// 1. Add Item at Beginning
// 2. Add Item at End
// 3. Add Item at Specific Position
// 4. Remove Item by ID
// 5. Update Quantity by Item ID
// 6. Search Item by ID
// 7. Search Item by Name
// 8. Calculate Total Inventory Value
// 9. Sort Inventory by name
// 10. Exit
// Enter choice: 10
// Exiting....