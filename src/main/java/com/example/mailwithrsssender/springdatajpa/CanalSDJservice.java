package com.example.mailwithrsssender.springdatajpa;

import com.example.mailwithrsssender.domain.Canal;
import com.example.mailwithrsssender.repositories.CanalRepository;
import com.example.mailwithrsssender.services.CanalService;
import org.springframework.stereotype.Service;
import java.util.Set;


@Service
public class CanalSDJservice implements CanalService {

    private final CanalRepository canalRepository;

    public CanalSDJservice(CanalRepository canalRepository) {
        this.canalRepository = canalRepository;
    }

    @Override
    public Set<Canal> findAll() {
        return null;
    }

    @Override
    public Canal findById(Long aLong) {
        return null;
    }



    @Override
    public Canal save(Canal canal) {
        return canalRepository.save(canal);
    }

    @Override
    public void delete(Canal o) {
        canalRepository.delete(o);

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void deleteAll() {
        canalRepository.deleteAll();
    }


    @Override
    public Set<Canal> findCanalByCustomerId(Long id) {
        return canalRepository.findCanalByCustomerId(id);
    }
}
