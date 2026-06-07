package com.sigith.feelink.dto;

import com.sigith.feelink.model.MessageType;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private Long id;
    private MessageType type;
    private String message;
}
