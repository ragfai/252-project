
package main;

import java.io.*;
import java.util.*;


public class OnlineArtStore {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("art.txt");

        if (!file.exists()) {
            System.out.println("Please Check The Input File");
            return;
        }

        Scanner input = new Scanner(file);
        ArrayList<Art> gallery = new ArrayList<>();

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.startsWith("#")) {
                continue;
            }

            String[] temp = line.split("\\s+");
            String artist = temp[0].replaceAll("_", " ");
            String title = temp[1].replaceAll("_", " ");
            String material = temp[2].replaceAll("_", " ");
            double price = Double.parseDouble(temp[3]);

            gallery.add(new Art(artist, title, material, price));
        }

        input = new Scanner(System.in);

        System.out.println("\n\nAre You: \n1: Employee \n2: Customer");

        boolean flag = true;
 
    
        
        for (; flag;) {
            int choice = input.nextInt();
         
            switch (choice) {
                case 1:
                    Employee employee = new Employee(gallery);
                    employee.employeeOperations(input);
                    flag = false;
                    break;
                case 2:
                    Customer customer = new Customer(gallery);
                    customer.customerOperations(input);
                    flag = false;
                    break;
                default:
                    System.out.println("Sorry, Wrong Choice.");
                    System.out.println("Please Reenter Your Choice.");
                    break;
            }
        }
    }
}
