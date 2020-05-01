package com.example.mailwithrsssender.repositories;

import com.example.mailwithrsssender.domain.Canal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface CanalRepository extends JpaRepository<Canal, Long> {

    Set<Canal> findCanalByCustomerId(Long id);
}
