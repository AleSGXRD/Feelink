package com.sigith.feelink.dto.friendship.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFriendshipRequestDTO {
    private String fromUserId;
    private String toUserId;
}
