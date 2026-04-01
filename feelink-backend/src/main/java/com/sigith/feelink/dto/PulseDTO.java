package com.sigith.feelink.dto;

import java.time.LocalDateTime;

public class PulseDTO {
    private String id;

    private UserDTO fromUser;
    private UserDTO toUser;

    private MessageDTO message;

    private LocalDateTime createdAt;
}
