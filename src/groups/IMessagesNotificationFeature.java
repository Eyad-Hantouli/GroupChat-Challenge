package groups;

import messages.Message;
import users.User;

public interface IMessagesNotificationFeature {
    void registerGroupMessagesNotificationService(User member);

    void unregisterGroupMessagesNotificationService(User member);

    void notifyMembersForSentMessage(Message message);
}
