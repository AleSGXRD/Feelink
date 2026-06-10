package com.sigith.feelink.repository;

import com.sigith.feelink.model.Friendship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFriendshipRepository extends JpaRepository<Friendship, String> {
    @Query("""
        SELECT f
        FROM Friendship f
        WHERE (
            (f.fromUser.id = :user1 AND f.toUser.id = :user2)
            OR
            (f.fromUser.id = :user2 AND f.toUser.id = :user1)
        )
    """)
    Optional<Friendship> existsActiveFriendship(
            String user1,
            String user2
    );

    @Query("""
        SELECT f
        FROM Friendship f
        WHERE (
            (f.fromUser.id = :userId)
            OR
            (f.toUser.id = :userId)
        )
    """)
    Page<Friendship> findActiveFriendships(
            String userId,
            Pageable pageable
    );
}
