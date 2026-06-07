package com.sigith.feelink.repository;

import com.sigith.feelink.model.Message;
import com.sigith.feelink.model.MessageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    @Query("""
        SELECT m
        FROM Message m
        WHERE (
            :type IS NULL
            OR m.type = :type
        )
    """)
    Page<Message> getMessages(MessageType type, Pageable pageable);
}
