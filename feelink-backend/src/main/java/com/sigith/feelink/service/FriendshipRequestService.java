package com.sigith.feelink.service;

import com.sigith.feelink.dto.friendship.CreateFriendshipDTO;
import com.sigith.feelink.dto.friendship.request.ResponseFriendshipRequestDTO;
import com.sigith.feelink.exception.ResourceNotFoundException;
import com.sigith.feelink.mapper.FriendshipRequestMapper;
import com.sigith.feelink.model.FriendshipRequest;
import com.sigith.feelink.model.User;
import com.sigith.feelink.repository.IFriendshipRequestRepository;
import com.sigith.feelink.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendshipRequestService {
    private final IFriendshipRequestRepository friendshipRequestRepository;
    private final IUserRepository userRepository;

    private final FriendshipRequestMapper friendshipRequestMapper;

    public ResponseFriendshipRequestDTO create(CreateFriendshipDTO dto){
        User fromUser = userRepository.findById(dto.getFromUserId())
                .orElseThrow(
                        () ->
                         new ResourceNotFoundException("Not found User:" + dto.getFromUserId())
                );
        User toUser = userRepository.findById(dto.getToUserId())
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException("Not found User:" + dto.getToUserId())
                );

        FriendshipRequest entity = friendshipRequestMapper.toEntity(fromUser, toUser);
        FriendshipRequest saved = friendshipRequestRepository.save(entity);

        return friendshipRequestMapper.toResponseDto(saved);
    }
}
