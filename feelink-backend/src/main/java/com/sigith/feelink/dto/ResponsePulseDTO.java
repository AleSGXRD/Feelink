package com.sigith.feelink.dto;

import com.sigith.feelink.model.MessageType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsePulseDTO {
    private String fromUserId;
    private String fromUserName;

    private String toUserId;
    private String toUserName;

    private Long messageId;
    private MessageType messageType;
    private String messageDetail;

    private LocalDateTime createdAt;
}
