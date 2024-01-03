import chat_system.Action;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Action actionObject = Action.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Add user (provide user ID)");
            System.out.println("2. Create group (provide group ID)");
            System.out.println("3. Add user to group (provide user ID and group ID)");
            System.out.println("4. Send message to group (provide user ID and group ID)");
            System.out.println("Enter 'exit' to end the program.");

            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            }

            try {
                int action = Integer.parseInt(userInput);
                performAction(action, scanner);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'exit'.");
            }
        }

        System.out.println("Program ended.");
    }

    static void performAction(int action, Scanner scanner) {
        switch (action) {
            case 1:
                actionObject.createUser(scanner);
                break;
            case 2:
                actionObject.createGroup(scanner);
                break;
            case 3:
                actionObject.addUserToGroup(scanner);
                break;
            case 4:
                actionObject.sendMessageToGroup(scanner);
                break;
            default:
                System.out.println("Invalid operation number. Please enter a number between 1 - 4.");
        }
    }

}