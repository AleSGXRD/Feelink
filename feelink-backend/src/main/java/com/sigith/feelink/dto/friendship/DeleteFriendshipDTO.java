package com.sigith.feelink.dto.friendship;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteFriendshipDTO {
    private String fromUserId;
    private String toUserId;
}
