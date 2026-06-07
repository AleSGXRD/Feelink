package com.sigith.feelink.dto.friendship.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFriendshipRequestDTO {
    private String fromUserId;
    private String toUserId;
    private boolean responded;
    private boolean accepted;
}
