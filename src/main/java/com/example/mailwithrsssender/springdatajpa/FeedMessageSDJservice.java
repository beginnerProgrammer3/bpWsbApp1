package com.example.mailwithrsssender.springdatajpa;

import com.example.mailwithrsssender.domain.FeedMessage;
import com.example.mailwithrsssender.repositories.FeedMessageRepository;
import com.example.mailwithrsssender.services.FeedMessageService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class FeedMessageSDJservice implements FeedMessageService {

    private final FeedMessageRepository feedMessageRepository;


    public FeedMessageSDJservice(FeedMessageRepository feedMessageRepository) {
        this.feedMessageRepository = feedMessageRepository;
    }


    @Override
    public Set<FeedMessage> findAll() {
        return null;
    }

    @Override
    public FeedMessage findById(Long aLong) {
        return null;
    }



    @Override
    public FeedMessage save(FeedMessage feedMessage) {
        System.out.println("Saving message");
        return feedMessageRepository.save(feedMessage);

    }

    @Override
    public void delete(FeedMessage o) {
        feedMessageRepository.deleteAll();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void deleteAll() {
        feedMessageRepository.deleteAll();
    }

    public void delete() {
        feedMessageRepository.deleteAll();
    }




}
