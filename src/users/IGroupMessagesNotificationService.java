package users;

import groups.Group;
import messages.Message;

public interface IGroupMessagesNotificationService {
    void notifyForNewMessage(Group group, User sender, Message message);
}
