package com.sigith.feelink.dto.friendship;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFriendshipDTO {
    private String fromUserId;
    private String toUserId;
}
