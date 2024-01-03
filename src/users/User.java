package users;

import groups.Group;
import messages.Message;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class User implements IGroupMessagesNotificationService, IGroupJoinLeaveMembersNotificationService {
    private long id;
    private static Map<Long, User> users = new HashMap<>();
    private String firstName;
    private String lastName;

    public User(UserBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;

        users.put(id, this);
    }

    public static boolean userExists(long id) {
        return users.containsKey(id);
    }

    public static User getUserById(long id) {
        return users.get(id);
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void notifyForMemberJoined(Group group, User user) {
        System.out.println("----------------------------------------------");

        System.out.println("User - " + id + " - (" + firstName + " " + lastName + ") - received a new notification.");
        System.out.println("Details: " + user.getFirstName() + " " + user.getLastName() +
                            " joined to group: " + group.getName());

        System.out.println("----------------------------------------------");
    }

    @Override
    public void notifyForMemberLeft(Group group, User user) {
        System.out.println("----------------------------------------------");

        System.out.println("User - " + id + " - (" + firstName + " " + lastName + ") - received a new notification.");
        System.out.println("Details: " + user.getFirstName() + " " + user.getLastName() +
                " left from group: " + group.getName());

        System.out.println("----------------------------------------------");
    }

    @Override
    public void notifyForNewMessage(Group group, User sender, Message message) {
        System.out.println("----------------------------------------------");

        System.out.println("User - " + id + " - (" + firstName + " " + lastName + ") - received a new notification.");
        System.out.println("Details: " + sender.getFirstName() + " " + sender.getLastName() +
                " sent a new message in group: " + group.getName() +
                " message content: " + message.getContent());

        System.out.println("----------------------------------------------");
    }


    public static class UserBuilder {
        // Mandatory data
        private long id;
        private String firstName;

        // Optional data
        private String lastName;

        public UserBuilder(long id, String firstName) {
            this.id = id;
            this.firstName = firstName;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        private void userValidation() {
            // some validations for user
        }

        public User build() {
            userValidation();
            return new User(this);
        }
    }
}
