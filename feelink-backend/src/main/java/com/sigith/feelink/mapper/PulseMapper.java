package com.sigith.feelink.mapper;

import com.sigith.feelink.dto.CreatePulseDTO;
import com.sigith.feelink.dto.ResponsePulseDTO;
import com.sigith.feelink.model.Message;
import com.sigith.feelink.model.Pulse;
import com.sigith.feelink.model.User;
import org.springframework.stereotype.Component;

@Component
public class PulseMapper {
    public ResponsePulseDTO toResponseDto(Pulse pulse){
        User fromUser = pulse.getFromUser();
        User toUser = pulse.getToUser();
        Message message = pulse.getMessage();

        return ResponsePulseDTO.builder()
                .fromUserId(fromUser.getId())
                .fromUserName(fromUser.getUsername())
                .toUserId(toUser.getId())
                .toUserName(toUser.getUsername())
                .messageId(message.getId())
                .messageDetail(message.getMessage())
                .messageType(message.getType())
                .build();
    }

    public Pulse toEntity(
            CreatePulseDTO dto,
            User fromUser,
            User toUser,
            Message message
    ) {
        Pulse pulse = new Pulse();

        pulse.setFromUser(fromUser);
        pulse.setToUser(toUser);
        pulse.setMessage(message);

        return pulse;
    }
}
