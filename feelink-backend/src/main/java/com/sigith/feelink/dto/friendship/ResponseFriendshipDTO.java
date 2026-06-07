package com.sigith.feelink.dto.friendship;

import java.time.LocalDateTime;

public class ResponseFriendshipDTO {
    private String fromUserId;
    private String fromUserName;

    private String toUserId;
    private String toUserName;

    private boolean accepted;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
