package com.sigith.feelink.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pulse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @CreatedDate
    private LocalDateTime createdAt;
}
