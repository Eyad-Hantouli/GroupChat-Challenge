package groups;

import users.User;

public interface IJoinLeaveMembersFeature {
    void registerForGroupJoinLeaveMembersNotificationService(User member);

    void unregisterFromGroupJoinLeaveMembersNotificationService(User member);

    void notifyMembersForMemberJoined(User joinedMember);

    void notifyMembersForMemberLeft(User leftMember);
}
