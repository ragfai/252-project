package main;

import java.util.ArrayList;

class Order {
    private ArrayList<Art> orderedArt = new ArrayList<>();

    public void addArtToOrder(Art art) {
        orderedArt.add(art);
    }

    public ArrayList<Art> getOrderedArt() {
        return orderedArt;
    }

    public void clearOrder() {
        orderedArt.clear();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Art art : orderedArt) {
            totalPrice += art.getPrice();
        }
        return totalPrice;
    }

    public void generateReceipt() {
        if (!orderedArt.isEmpty()) {
            System.out.println("\nYour Order Receipt:");
            System.out.println("\n-------------------------------------------------");
            for (Art art : orderedArt) {
                System.out.println("Title: " + art.getTitle());
                System.out.println("Artist: " + art.getArtist());
                System.out.println("Material: " + art.getMaterial());
                System.out.println("Price: $" + art.getPrice());
                System.out.println("\n-------------------------------------------------");
            }
            System.out.println("Total Price: $" + calculateTotalPrice());
            System.out.println("\n-------------------------------------------------");
        } else {
            System.out.println("\nYour Cart is Empty. Nothing to Generate a Receipt for.");
        }
    }
}