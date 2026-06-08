package com.sigith.feelink.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendshipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Builder.Default
    private boolean responded = false;
    @Builder.Default
    private boolean accepted = false;

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
