package com.sigith.feelink.dto.friendship;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseFriendshipDTO {
    private String fromUserId;
    private String fromUserName;

    private String toUserId;
    private String toUserName;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
