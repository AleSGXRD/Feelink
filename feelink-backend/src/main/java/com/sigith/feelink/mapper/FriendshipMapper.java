package com.sigith.feelink.mapper;

import com.sigith.feelink.dto.friendship.ResponseFriendshipDTO;
import com.sigith.feelink.model.Friendship;
import com.sigith.feelink.model.User;
import org.springframework.stereotype.Component;

@Component
public class FriendshipMapper {

    public ResponseFriendshipDTO toResponseDto(Friendship friendship){
        User toUser = friendship.getToUser();
        User fromUser = friendship.getFromUser();
        return ResponseFriendshipDTO.builder()
                .toUserName(toUser.getUsername())
                .toUserId(toUser.getId())
                .fromUserName(fromUser.getUsername())
                .fromUserId(fromUser.getId())
                .createdAt(friendship.getCreatedAt())
                .build();
    }

    public Friendship toEntity(User fromUser, User toUser){
        return Friendship.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();
    }
}
