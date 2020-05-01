package com.example.mailwithrsssender.repositories;

import com.example.mailwithrsssender.domain.FeedMessage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedMessageRepository extends JpaRepository<FeedMessage, Long> {


}
