package com.sigith.feelink.repository;

import com.sigith.feelink.model.Pulse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPulseRepository extends JpaRepository<Pulse, String> {
    @Query("""
        SELECT p
        FROM Pulse p
        WHERE p.fromUser.id = :userId
        AND (
            :toUserId IS NULL
            OR p.toUser.id = :toUserId
        )
        AND p.deletedBySenderAt IS NULL
    """)
    Page<Pulse> findSentPulses(
            String userId,
            String toUserId,
            Pageable pageable
    );

    @Query("""
        SELECT p
        FROM Pulse p
        WHERE p.toUser.id = :userId
        AND (
            :fromUserId IS NULL
            OR p.fromUserId.id = :fromUserId
        )
        AND p.deletedByReceiverAt IS NULL
    """)
    Page<Pulse> findReceivedPulses(
            String userId,
            String fromUserId,
            Pageable pageable
    );
}
