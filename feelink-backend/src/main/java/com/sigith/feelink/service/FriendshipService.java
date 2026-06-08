package com.sigith.feelink.service;

import com.sigith.feelink.dto.friendship.ResponseFriendshipDTO;
import com.sigith.feelink.exception.ConflictException;
import com.sigith.feelink.mapper.FriendshipMapper;
import com.sigith.feelink.model.Friendship;
import com.sigith.feelink.model.User;
import com.sigith.feelink.repository.IFriendshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FriendshipService {

    private final IFriendshipRepository friendshipRepository;

    private final FriendshipMapper friendshipMapper;

    public ResponseFriendshipDTO create(User fromUser, User toUser){
        Friendship friendship = friendshipMapper.toEntity(fromUser, toUser);

        if(isFriendshipActive(fromUser, toUser)){
            throw new ConflictException("Friendship already exists");
        }

        Friendship saved = friendshipRepository.save(friendship);

        return friendshipMapper.toResponseDto(saved);
    }

    public boolean isFriendshipActive(User fromUser, User toUser){
        Optional<Friendship> isActive = friendshipRepository.findActiveFriendship(fromUser.getId(), toUser.getId());

        return isActive.isPresent();
    }
}
