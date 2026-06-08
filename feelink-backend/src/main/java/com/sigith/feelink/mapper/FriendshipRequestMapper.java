package com.sigith.feelink.mapper;

import com.sigith.feelink.dto.friendship.request.ResponseFriendshipRequestDTO;
import com.sigith.feelink.model.FriendshipRequest;
import com.sigith.feelink.model.User;
import org.springframework.stereotype.Component;

@Component
public class FriendshipRequestMapper {

    public FriendshipRequest toEntity(User fromUser, User toUser){
        return FriendshipRequest.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();
    }

    public ResponseFriendshipRequestDTO toResponseDto(FriendshipRequest friendshipRequest){
        return ResponseFriendshipRequestDTO.builder()
                .fromUserId(friendshipRequest.getFromUser().getId())
                .fromUserName(friendshipRequest.getFromUser().getUsername())
                .toUserId(friendshipRequest.getToUser().getId())
                .toUserName(friendshipRequest.getToUser().getUsername())
                .accepted(friendshipRequest.isAccepted())
                .responded(friendshipRequest.isResponded())
                .createdAt(friendshipRequest.getCreatedAt())
                .build();
    }
}
