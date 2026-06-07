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
        WHERE p.fromUser.id = :fromUserId
        AND (
            :toUserId IS NULL
            OR p.toUser.id = :toUserId
        )
        AND p.deletedBySenderAt IS NULL
    """)
    Page<Pulse> findSentPulses(
            String fromUserId,
            String toUserId,
            Pageable pageable
    );

    @Query("""
        SELECT p
        FROM Pulse p
        WHERE p.toUser.id = :toUserId
        AND (
            :fromUserId IS NULL
            OR p.fromUser.id = :fromUserId
        )
        AND p.deletedByReceiverAt IS NULL
    """)
    Page<Pulse> findReceivedPulses(
            String toUserId,
            String fromUserId,
            Pageable pageable
    );
}
