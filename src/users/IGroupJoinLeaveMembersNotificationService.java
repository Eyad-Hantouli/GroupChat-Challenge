package users;


import groups.Group;

public interface IGroupJoinLeaveMembersNotificationService {
    void notifyForMemberJoined(Group group, User user);

    void notifyForMemberLeft(Group group, User user);
}
