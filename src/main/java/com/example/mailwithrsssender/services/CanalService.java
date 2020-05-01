package com.example.mailwithrsssender.services;

import com.example.mailwithrsssender.domain.Canal;

import java.util.Set;

public interface CanalService extends CrudService<Canal, Long> {

    Set<Canal> findCanalByCustomerId(Long id);

}
