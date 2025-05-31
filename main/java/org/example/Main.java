package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[L]ogin, [S]ign up: ");
        String choice = scanner.nextLine().trim().toLowerCase();

        try {
            if (choice.equals("l") || choice.equals("login")) {
                loginProcess(scanner);
            } else if (choice.equals("s") || choice.equals("sign up")) {
                signUpProcess(scanner);
            } else {
                System.out.println("Invalid choice");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void loginProcess(Scanner scanner) {
        System.out.println("Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Password: ");
        String password = scanner.nextLine().trim();

        User user = UserService.login(email, password);
        System.out.println("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
    }

    private static void signUpProcess(Scanner scanner) {
        System.out.println("First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.println("Last Name: ");
        String lastName = scanner.nextLine().trim();

        System.out.println("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Password: ");
        String password = scanner.nextLine().trim();

        UserService.signUp(firstName, lastName, age, email, password);
        System.out.println("Registration successful!");
    }
}