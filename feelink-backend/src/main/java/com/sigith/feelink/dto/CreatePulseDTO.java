package com.sigith.feelink.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePulseDTO {
    private String fromUserId;
    private String toUserId;
    private String messageId;
}
