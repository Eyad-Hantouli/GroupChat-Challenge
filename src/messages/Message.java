package messages;

import groups.Group;
import users.User;

import java.time.LocalDate;

public class Message {
    private User sender;
    private Group group;
    private String content;
    private LocalDate sentDate;

    public Message(Group group, User sender, String content) {
        this.group = group;
        this.sender = sender;
        this.content = content;
        this.sentDate = LocalDate.now();
    }

    public Group getGroup() {
        return group;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }
}
