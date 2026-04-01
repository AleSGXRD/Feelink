package com.sigith.feelink.dto;

import java.time.LocalDateTime;

public class FriendshipDTO {
    private String id;
    private boolean accepted;

    private UserDTO fromUser;
    private UserDTO toUser;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
