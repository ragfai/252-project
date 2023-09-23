package main;

import java.util.ArrayList;
import java.util.Scanner;

class Customer extends User{
    private ArrayList<Art> gallery;
    private Order order;

    public Customer(ArrayList<Art> gallery) {
        this.gallery = gallery;
        this.order = new Order();
    }

    public void customerOperations(Scanner input) {
        login(input);
        while (true) {
            displayCustomerMenu(order.getOrderedArt().size());
            System.out.print("Please, Select Your Choice: ");
            int choice = input.nextInt();

            if(order.getOrderedArt().size() != 0){
            switch (choice) {
                case 1:
                    addArtToCart(input);
                    break;
                case 2:
                    deleteArt(input);
                    break;
                case 3:
                    System.out.print("\nPlease, Enter The Art Title Or The Artist: ");
                    input.nextLine();
                    String name = input.nextLine().trim().replaceAll(" ", "_");
                    searchForArt(name);
                    break;                                    
                case 4:
                    confirmOrder();
                    break;
                case 5:
                    return;
            }
            }else{
                switch (choice) {
                case 1:
                    addArtToCart(input);
                    break;
                case 2:
                    System.out.print("\nPlease, Enter The Art Title Or The Artist: ");
                    input.nextLine();
                    String name = input.nextLine().trim().replaceAll(" ", "_");
                    searchForArt(name);
                    break;
                case 3:
                    return;
                    }
        }
        }
    }

    private void searchForArt(String name) {
        int counter = 0;
        ArrayList<Art> result = new ArrayList<>();
        for (int i = 0; i < gallery.size(); i++) {
            if (name.equalsIgnoreCase(gallery.get(i).getTitle()) ||
                name.equalsIgnoreCase(gallery.get(i).getArtist())) {
                System.out.println(++counter + ": " + gallery.get(i));
                result.add(gallery.get(i));
            }
        }

        if (counter == 0) {
            System.out.println("\nSorry, There Is No Art By This Title Or Artist.\n");
        }
    }

    private void deleteArt(Scanner input) {
        if (!order.getOrderedArt().isEmpty()) {
            System.out.println("\n\nThe Art(s) You Have In Cart: ");
            displayCart(order);

            System.out.print("Please, Select The Art You Want To Delete (Press 0 To Go Back). ");
            while (true) {
                int selected = input.nextInt();
                if (selected == 0) {
                    break;
                } else if (1 <= selected && selected <= order.getOrderedArt().size()) {
                    System.out.print("Are You Sure You Want To Delete The Art " + order.getOrderedArt().get(selected - 1).getTitle() + " (yes/no)?");
                    while (true) {
                        String choice = input.next();
                        if (choice.equalsIgnoreCase("yes")) {
                            deleteArtFromCart(order.getOrderedArt().get(selected - 1));
                            System.out.println("\nArt deleted successfully!\n");
                            break;
                        } else if (choice.equalsIgnoreCase("no")) {
                            break;
                        } else {
                            System.out.println("\nWrong Input, Try Again!\n");
                        }
                    }
                    break;
                } else {
                    System.out.println("\nWrong Input, Try Again!\n");
                }
            }
        } else {
            System.out.println("\nShopping Cart is empty!\n");
        }
    }

    private void displayCart(Order order) {
        int counter = 1;
        for (Art tempArt : order.getOrderedArt()) {
            System.out.println(counter++ + ".\t" + tempArt + " ");
            System.out.println("\n-------------------------------------------------");
        }
        // Display the total price of items in the shopping cart
        System.out.println("Total Price: $" + order.calculateTotalPrice());
        System.out.println("\n-------------------------------------------------");
    }

    private void addArtToCart(Scanner input) {
        displayGallery();
        System.out.print("Please, Select The Art You Want To Add (Press 0 To Go Back). ");

        while (true) {
            int selected = input.nextInt();
            if (selected == 0) {
                break;
            } else if (1 <= selected && selected <= gallery.size()) {
                System.out.print("Are You Sure You Want To Add The Art " + gallery.get(selected - 1).getTitle().replaceAll("_", " ") + " (yes/no)?");
                while (true) {
                    String choice = input.next();
                    if (choice.equalsIgnoreCase("yes")) {
                        addArtToCart(order, gallery.get(selected - 1));
                        System.out.println("Art Added successfully To The Cart!");
                        break;
                    } else if (choice.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        System.out.println("\nWrong Input, Try Again!\n");
                    }
                }
                break;
            } else {
                System.out.println("\nWrong Input, Try Again!\n");
            }
        }
    }

    private void displayGallery() {
        if (gallery.isEmpty()) {
            System.out.println("There are No Arts Available!");
        } else {
            int counter = 1;
            for (Art art : gallery) {
                System.out.println(counter++ + ". " + art);
            }
        }
    }

    private void displayCustomerMenu(int cartSize) {
        if (cartSize != 0) {
            System.out.println("\n1: Add Art To Cart."
                    + "\n2: Delete Art from Cart."
                    + "\n3: Search For Art."
                    + "\n4: Confirm Order."
                    + "\n5: Quit.");
            
        } else if (cartSize == 0){
            System.out.println("\n1: Add Art To Cart."
                    + "\n2: Search For Art."                   
                    + "\n3: Quit.");
        }
    }

    private void addArtToCart(Order order, Art art) {
        order.addArtToOrder(art);
    }

    private void deleteArtFromCart(Art art) {
        order.getOrderedArt().remove(art);
    }

    private void confirmOrder() {
        if (!order.getOrderedArt().isEmpty()) {
            System.out.println("\nYour Cart Contains The Following Art(s): ");
            displayCart(order);
            System.out.println("\nConfirm Your Order (yes/no)?");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("\nThank You! Your Order Has Been Confirmed.");

                order.generateReceipt();
                order.clearOrder();
            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("\nYour Order Has Been Cancelled.");
            } else {
                System.out.println("\nInvalid Choice. Order Not Confirmed.");
            }
        } else {
            System.out.println("\nYour Cart is Empty. Nothing to Confirm.");
        }
        
        
    }
}
