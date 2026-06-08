package com.sigith.feelink.repository;

import com.sigith.feelink.model.FriendshipRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IFriendshipRequestRepository extends JpaRepository<FriendshipRequest, String> {
    @Query("""
        SELECT fr
        FROM FriendshipRequest fr
        WHERE  fr.fromUser.id = :userId
        AND p.deletedBySenderAt IS NULL
        AND (:pending = false OR fr.responded = false)
    """)
    Page<FriendshipRequest> getSentFriendshipRequests(String userId, boolean pending, Pageable pageable);

    @Query("""
        SELECT fr
        FROM FriendshipRequest fr
        WHERE  fr.toUser.id = :userId
        AND p.deletedBySenderAt IS NULL
        AND (:pending = false OR fr.responded = false)
    """)
    Page<FriendshipRequest> getReceivedFriendshipRequests(String userId, boolean pending, Pageable pageable);
}
