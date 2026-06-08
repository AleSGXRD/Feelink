package com.sigith.feelink.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private boolean responded;
    private boolean accepted;

    @ManyToOne()
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;
    @ManyToOne()
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime deletedBySenderAt;
    private LocalDateTime deletedByReceiverAt;
}
