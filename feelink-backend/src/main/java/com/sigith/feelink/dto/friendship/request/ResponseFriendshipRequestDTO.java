package com.sigith.feelink.dto.friendship.request;

import java.time.LocalDateTime;

public class ResponseFriendshipRequestDTO {
    private String fromUserId;
    private String fromUserName;

    private String toUserId;
    private String toUserName;

    private boolean responded;
    private boolean accepted;
    private LocalDateTime createdAt;
}
