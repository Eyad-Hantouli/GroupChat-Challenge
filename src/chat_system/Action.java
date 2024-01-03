package chat_system;

import groups.Group;
import messages.Message;
import users.User;

import java.util.Scanner;

public class Action {
    private static Action action = new Action();

    private Action() {}

    public static Action getInstance () {
        return action;
    }

    public void createUser (Scanner scanner) {
        System.out.println("**************");
        System.out.println("** Add User **");
        System.out.println("**************\n");

        System.out.print("Enter user id: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println();

        if (User.userExists(id)) {
            System.out.println("Sorry, this user id is already taken.");
            System.out.println("**************\n");
            return;
        }

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println();

        new User.UserBuilder(id, firstName).setLastName(lastName).build();

        System.out.println("User has been created successfully.");
        System.out.println("**************\n");
    }

    public void createGroup (Scanner scanner) {
        System.out.println("******************");
        System.out.println("** Create Group **");
        System.out.println("******************\n");

        System.out.print("Enter group id: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println();

        if (Group.groupExists(id)) {
            System.out.println("Sorry, this group id is already taken.");
            System.out.println("**************\n");
            return;
        }

        System.out.print("Enter group name: ");
        String name = scanner.nextLine();
        System.out.println();

        new Group.GroupBuilder(id, name).build();

        System.out.println("Group has been created successfully.");
        System.out.println("**************\n");
    }

    public void addUserToGroup (Scanner scanner) {
        System.out.println("***********************");
        System.out.println("** Add user to group **");
        System.out.println("***********************\n");

        System.out.print("Enter user id: ");
        long userId = scanner.nextLong();
        scanner.nextLine();
        System.out.println();

        if (!User.userExists(userId)) {
            System.out.println("Sorry, user doesn't exists.");
            System.out.println("**************\n");
            return;
        }

        System.out.print("Enter group id: ");
        long groupId = scanner.nextLong();
        scanner.nextLine();
        System.out.println();

        if (!Group.groupExists(groupId)) {
            System.out.println("Sorry, group doesn't exists.");
            System.out.println("**************\n");
            return;
        }

        User user = User.getUserById(userId);
        Group group = Group.getGroupById(groupId);
        group.addMember(user);

        System.out.println("Member added to group successfully.");
        System.out.println("**************\n");
    }

    public void sendMessageToGroup (Scanner scanner) {
        System.out.println("***************************");
        System.out.println("** Send message to group **");
        System.out.println("***************************\n");

        System.out.print("Enter user id: ");
        long userId = scanner.nextLong();
        scanner.nextLine();
        System.out.println();

        if (!User.userExists(userId)) {
            System.out.println("Sorry, user doesn't exists.");
            System.out.println("**************\n");
            return;
        }

        System.out.print("Enter group id: ");
        long groupId = scanner.nextLong();
        scanner.nextLine();
        System.out.println();

        if (!Group.groupExists(groupId)) {
            System.out.println("Sorry, group doesn't exists.");
            System.out.println("**************\n");
            return;
        }

        System.out.print("Enter message content id: ");
        String messageContent = scanner.nextLine();
        System.out.println();

        User user = User.getUserById(userId);
        Group group = Group.getGroupById(groupId);

        group.sendMessage(new Message(group, user, messageContent));

        System.out.println("Message sent successfully.");
        System.out.println("**************\n");
    }
}
