package com.sigith.feelink.repository;

import com.sigith.feelink.model.Friendship;
import com.sigith.feelink.model.FriendshipRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFriendshipRequestRepository extends JpaRepository<FriendshipRequest, String> {
    @Query("""
        SELECT fr
        FROM FriendshipRequest fr
        WHERE  fr.fromUser.id = :userId
        AND fr.deletedBySenderAt IS NULL
        AND (:pending = false OR fr.responded = false)
    """)
    Page<FriendshipRequest> getSentFriendshipRequests(String userId, boolean pending, Pageable pageable);

    @Query("""
        SELECT fr
        FROM FriendshipRequest fr
        WHERE  fr.toUser.id = :userId
        AND fr.deletedByReceiverAt IS NULL
        AND (:pending = false OR fr.responded = false)
    """)
    Page<FriendshipRequest> getReceivedFriendshipRequests(String userId, boolean pending, Pageable pageable);

    @Query("""
        SELECT COUNT(fr) > 0
        FROM FriendshipRequest fr
        WHERE fr.responded IS FALSE
        AND (
            (fr.fromUser.id = :user1 AND fr.toUser.id = :user2)
            OR
            (fr.fromUser.id = :user2 AND fr.toUser.id = :user1)
        )
    """)
    boolean existsActiveFriendshipRequest(
            String user1,
            String user2
    );
}
