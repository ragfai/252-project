
package main;

import java.util.Scanner;

public class User {
    public String phone;
    public String password;


    public void login(Scanner input) {
        System.out.print("\nEnter your phone number: ");
        phone = input.next();
        System.out.print("Enter your password: ");
        password = input.next();
    }

    // Getters and setters for phone and password attributes
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
