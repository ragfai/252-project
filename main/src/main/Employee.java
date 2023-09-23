
package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


class Employee extends User{
    private ArrayList<Art> gallery;

    public Employee(ArrayList<Art> gallery) {
        this.gallery = gallery;
    }
    

    public void employeeOperations(Scanner input) {
        login(input);
        while (true) {
            displayEmployeeMenu();
            System.out.print("Please Select An Operation From The Menu: ");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    addArtToWebsite(input);
                    break;
                case 2:
                    return;
            }
        }
    }

    private void addArtToWebsite(Scanner input) {
        System.out.print("Please, Enter The Title Of The Art: ");
        input.nextLine();
        String title = input.nextLine().trim().replaceAll(" ", "_");
        System.out.print("\nPlease, Enter The Artist Of The Art: ");
        String artist = input.nextLine().trim().replaceAll(" ", "_");
        System.out.print("\nPlease, Enter The Material used for the Art: ");
        String material = input.nextLine().trim().replaceAll(" ", "_");

        // Prompt for the price
        System.out.print("\nPlease, Enter The Price Of The Art: ");
        double price = input.nextDouble();

        gallery.add(new Art(artist, title, material, price)); // Pass the price to the Art constructor
        updateDataBase("art.txt", gallery);

        System.out.println("The Website And The Database Have Been Updated");
        System.out.println("The library contains: ");
        int counter = 1;
        for (Art art : gallery) {
            System.out.println(counter++ + ". " + art);
        }
    }

    private void updateDataBase(String fileName, ArrayList<Art> gallery) {
        try {
            PrintWriter pen = new PrintWriter(fileName);
            pen.println("#Title   Artist   Material   Price");
            for (Art art : gallery) {
                pen.println(art.getTitle() + " " + art.getArtist() + " " + art.getMaterial() + " " + art.getPrice());
            }
            pen.flush();
            pen.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void displayEmployeeMenu() {
        System.out.println("1: Add Art To The Library."
                + "\n2: Quit.");
    }
}