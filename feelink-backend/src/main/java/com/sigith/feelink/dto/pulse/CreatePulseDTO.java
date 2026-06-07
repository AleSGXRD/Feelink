package com.sigith.feelink.dto.pulse;

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
