package com.sigith.feelink.dto.pulse;

import com.sigith.feelink.model.MessageType;

import java.time.LocalDateTime;

public class ResponsePulseDTO {
    private String fromUserId;
    private String fromUserName;

    private String toUserId;
    private String toUserName;

    private String messageId;
    private MessageType messageType;
    private String messageDetail;

    private LocalDateTime createdAt;
}
