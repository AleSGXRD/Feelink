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
    """)
    Page<Pulse> findSentPulses(
            String fromUserId,
            String toUserId,
            Pageable pageable
    );
}
