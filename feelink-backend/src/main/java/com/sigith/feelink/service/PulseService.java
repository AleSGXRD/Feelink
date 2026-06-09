package com.sigith.feelink.service;

import com.sigith.feelink.dto.CreatePulseDTO;
import com.sigith.feelink.dto.ResponsePulseDTO;
import com.sigith.feelink.exception.ResourceNotFoundException;
import com.sigith.feelink.mapper.PulseMapper;
import com.sigith.feelink.model.Friendship;
import com.sigith.feelink.model.Message;
import com.sigith.feelink.model.Pulse;
import com.sigith.feelink.model.User;
import com.sigith.feelink.repository.IFriendshipRepository;
import com.sigith.feelink.repository.IMessageRepository;
import com.sigith.feelink.repository.IPulseRepository;
import com.sigith.feelink.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PulseService {
    private final IPulseRepository pulseRepository;
    private final IUserRepository userRepository;
    private final IMessageRepository messageRepository;
    private final IFriendshipRepository friendshipRepository;

    private final PulseMapper pulseMapper;

    public ResponsePulseDTO create(CreatePulseDTO dto){
        User fromUser = userRepository.findById(dto.getFromUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + dto.getFromUserId()
                        ));

        User toUser = userRepository.findById(dto.getToUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + dto.getToUserId()
                        ));

        Message message = messageRepository.findById(dto.getMessageId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Message not found: " + dto.getMessageId()
                        ));

        friendshipRepository
                .existsActiveFriendship(
                        dto.getFromUserId(),
                        dto.getToUserId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Users are not friends"
                        )
                );

        Pulse pulse = pulseMapper.toEntity(dto, fromUser, toUser, message);

        Pulse saved = pulseRepository.save(pulse);

        return pulseMapper.toResponseDto(saved);
    }

    public Page<ResponsePulseDTO> getSentPulses(
            String userId,
            String toUserId,
            int page,
            int size
    ){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return pulseRepository
                .findSentPulses(userId, toUserId, pageable)
                .map(pulseMapper::toResponseDto);
    }

    public Page<ResponsePulseDTO> getReceivedPulses(
            String userId,
            String fromUserId,
            int page,
            int size
    ){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return pulseRepository
                .findReceivedPulses(userId, fromUserId, pageable)
                .map(pulseMapper::toResponseDto);
    }

    @Transactional
    public void deletePulse(
            String pulseId,
            String userId
    ) throws AccessDeniedException {
        Pulse pulse = pulseRepository.findById(pulseId).
                        orElseThrow(()->
                                new ResourceNotFoundException(
                                        "Pulse not found: " + pulseId
                                ));

        boolean isSender = pulse.getFromUser().getId().equals(userId);
        boolean isReceiver = pulse.getToUser().getId().equals(userId);

        if(!isReceiver && !isSender){
            throw new AccessDeniedException(
                    "User does not belong to this pulse"
            );
        }

        if(isSender){
            pulse.setDeletedBySenderAt(
                    LocalDateTime.now()
            );
        }

        if(isReceiver){
            pulse.setDeletedByReceiverAt(
                    LocalDateTime.now()
            );
        }

        if(
            pulse.getDeletedBySenderAt() != null &&
            pulse.getDeletedByReceiverAt() != null
        ){
            pulseRepository.delete(pulse);
        }

        pulseRepository.save(pulse);
    }
}
