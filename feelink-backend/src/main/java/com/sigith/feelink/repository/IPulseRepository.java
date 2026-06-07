package com.sigith.feelink.repository;

import com.sigith.feelink.model.Pulse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPulseRepository extends JpaRepository<Pulse, String> {
}
