package com.sigith.feelink.service;

import com.sigith.feelink.dto.friendship.CreateFriendshipDTO;
import com.sigith.feelink.dto.friendship.ResponseFriendshipDTO;
import com.sigith.feelink.dto.friendship.request.ResponseFriendshipRequestDTO;
import com.sigith.feelink.exception.ConflictException;
import com.sigith.feelink.exception.ResourceNotFoundException;
import com.sigith.feelink.mapper.FriendshipRequestMapper;
import com.sigith.feelink.model.Friendship;
import com.sigith.feelink.model.FriendshipRequest;
import com.sigith.feelink.model.User;
import com.sigith.feelink.repository.IFriendshipRequestRepository;
import com.sigith.feelink.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class FriendshipRequestService {
    private final IFriendshipRequestRepository friendshipRequestRepository;
    private final IUserRepository userRepository;

    private final FriendshipRequestMapper friendshipRequestMapper;

    private final FriendshipService friendshipService;

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

        boolean isActive = friendshipService.isFriendshipActive(fromUser, toUser);

        if(isActive){
            throw new ConflictException(
                    "Friendship already exists"
            );
        }

        FriendshipRequest entity = friendshipRequestMapper.toEntity(fromUser, toUser);
        FriendshipRequest saved = friendshipRequestRepository.save(entity);

        return friendshipRequestMapper.toResponseDto(saved);
    }

    @Transactional
    public ResponseFriendshipRequestDTO acceptRequest(
            String userId,
            String friendshipRequestId,
            boolean accept
    ) throws AccessDeniedException {
        FriendshipRequest friendshipRequest = friendshipRequestRepository.findById(friendshipRequestId)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException("Not found frienship request: " + friendshipRequestId)
                );

        if(!friendshipRequest.getToUser().getId().equals(userId)){
            throw new AccessDeniedException(
                    "You are not allowed to respond to this friendship request"
            );
        }

        if(friendshipRequest.isResponded()){
            throw new AccessDeniedException(
                    "Friendship request has already been responded to"
            );
        }

        friendshipRequest.setAccepted(accept);
        friendshipRequest.setResponded(true);

        FriendshipRequest saved = friendshipRequestRepository.save(friendshipRequest);

        if(accept){
            User fromUser = friendshipRequest.getFromUser();
            User toUser = friendshipRequest.getToUser();
            ResponseFriendshipDTO friendship = friendshipService.create(fromUser, toUser);
        }

        return friendshipRequestMapper.toResponseDto(saved);
    }

    public Page<FriendshipRequest> getSentFriendshipRequests(
            String userId,
            boolean pending,
            int page,
            int size
    ){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return friendshipRequestRepository.getSentFriendshipRequests(userId, pending, pageable);
    }

    public Page<FriendshipRequest> getReceivedFriendshipRequests(
            String userId,
            boolean pending,
            int page,
            int size
    ){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return friendshipRequestRepository.getReceivedFriendshipRequests(userId, pending, pageable);
    }

    public void deleteFriendshipRequest(
            String userId,
            String requestId
    ) throws AccessDeniedException{
        FriendshipRequest friendshipRequest = friendshipRequestRepository.findById(requestId)
                .orElseThrow(
                        () ->
                            new ResourceNotFoundException("Friendship request not found: " + requestId)
                );

        boolean isSender = friendshipRequest.getFromUser().getId().equals(userId);
        boolean isReceiver = friendshipRequest.getToUser().getId().equals(userId);

        if(!isReceiver && !isSender){
            throw new AccessDeniedException(
                    "User can't access to this friendship request"
            );
        }

        if (isSender){
            if(friendshipRequest.getDeletedBySenderAt() != null){
                throw new ConflictException(
                        "Friendship request already deleted by user"
                );
            }
            if(!friendshipRequest.isResponded()){
                friendshipRequestRepository.delete(friendshipRequest);
                return;
            }
            else{
                friendshipRequest.setDeletedBySenderAt(
                        LocalDateTime.now()
                );
            }
        }

        if(isReceiver){
            if(friendshipRequest.getDeletedByReceiverAt() != null){
                throw new ConflictException(
                        "Friendship request already deleted by user"
                );
            }

            friendshipRequest.setDeletedByReceiverAt(
                    LocalDateTime.now()
            );

            if (!friendshipRequest.isResponded()){
                friendshipRequest.setResponded(true);
                friendshipRequest.setAccepted(false);
            }
        }


        if(friendshipRequest.getDeletedByReceiverAt() != null &&
            friendshipRequest.getDeletedBySenderAt() != null){
            friendshipRequestRepository.delete(friendshipRequest);
            return;
        }

        friendshipRequestRepository.save(friendshipRequest);
    }
}
