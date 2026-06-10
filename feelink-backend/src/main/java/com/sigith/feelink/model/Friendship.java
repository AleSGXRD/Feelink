package com.sigith.feelink.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne()
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;
    @ManyToOne()
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;

    @CreatedDate
    private LocalDateTime createdAt;
}
