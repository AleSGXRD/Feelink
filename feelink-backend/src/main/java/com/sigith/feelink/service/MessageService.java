package com.sigith.feelink.service;

import com.sigith.feelink.model.Message;
import com.sigith.feelink.model.MessageType;
import com.sigith.feelink.repository.IMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {
    private final IMessageRepository messageRepository;

    public Page<Message> getAvailableMessages(
            MessageType messageType,
            int page,
            int size
    ){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("type").descending()
        );

        return messageRepository.getMessages(messageType, pageable);
    }

    public Optional<Message> getMessageById(Long messageId){
        return messageRepository.findById(messageId);
    }
}
