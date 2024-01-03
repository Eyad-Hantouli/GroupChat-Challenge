package groups;

import messages.Message;
import users.IGroupJoinLeaveMembersNotificationService;
import users.IGroupMessagesNotificationService;
import users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group implements IJoinLeaveMembersFeature, IMessagesNotificationFeature {
    private long id;
    private static Map<Long, Group> groups = new HashMap<>();
    private String name;
    private LocalDate creationDate;
    private List<User> members;
    private List<IGroupJoinLeaveMembersNotificationService> groupJoinLeaveMembersNotificationServiceSubscribers;
    private List<IGroupMessagesNotificationService> groupMessagesNotificationServiceSubscribers;
    private List<Message> messages;

    public Group(GroupBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.creationDate = LocalDate.now();
        this.members = new ArrayList<>();
        this.groupJoinLeaveMembersNotificationServiceSubscribers = new ArrayList<>();
        this.groupMessagesNotificationServiceSubscribers = new ArrayList<>();
        this.messages = new ArrayList<>();

        groups.put(id, this);
    }

    public static boolean groupExists(long id) {
        return groups.containsKey(id);
    }

    public static Group getGroupById(long id) {
        return groups.get(id);
    }

    public void addMember(User user) {
        if (members.contains(user)) {
            System.out.println("Group Class Error: Member is already in this group.");
            return;
        }
        members.add(user);
        registerForGroupJoinLeaveMembersNotificationService(user);
        registerGroupMessagesNotificationService(user);
        notifyMembersForMemberJoined(user);
    }

    public void removeMember(User user) {
        members.remove(user);
        unregisterFromGroupJoinLeaveMembersNotificationService(user);
        unregisterGroupMessagesNotificationService(user);
        notifyMembersForMemberLeft(user);
    }

    public void sendMessage(Message message) {
        messages.add(message);
        notifyMembersForSentMessage(message);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public void registerForGroupJoinLeaveMembersNotificationService(User member) {
        groupJoinLeaveMembersNotificationServiceSubscribers.add(member);
    }

    @Override
    public void unregisterFromGroupJoinLeaveMembersNotificationService(User member) {
        groupJoinLeaveMembersNotificationServiceSubscribers.remove(member);
    }

    @Override
    public void notifyMembersForMemberJoined(User joinedMember) {
        for (IGroupJoinLeaveMembersNotificationService user : groupJoinLeaveMembersNotificationServiceSubscribers) {
            user.notifyForMemberJoined(this, joinedMember);
        }
    }

    @Override
    public void notifyMembersForMemberLeft(User leftMember) {
        for (IGroupJoinLeaveMembersNotificationService user : groupJoinLeaveMembersNotificationServiceSubscribers) {
            user.notifyForMemberLeft(this, leftMember);
        }
    }

    @Override
    public void registerGroupMessagesNotificationService(User member) {
        groupMessagesNotificationServiceSubscribers.add(member);
    }

    @Override
    public void unregisterGroupMessagesNotificationService(User member) {
        groupMessagesNotificationServiceSubscribers.remove(member);
    }

    @Override
    public void notifyMembersForSentMessage(Message message) {
        for (IGroupMessagesNotificationService user : groupMessagesNotificationServiceSubscribers) {
            user.notifyForNewMessage(this, message.getSender(), message);
        }
    }

    public static class GroupBuilder {
        // Mandatory data
        private long id;
        private String name;

        public GroupBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        private void groupValidation() {
            // some validations for group
        }

        public Group build() {
            groupValidation();
            return new Group(this);
        }
    }
}
