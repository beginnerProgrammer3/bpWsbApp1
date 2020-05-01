package com.example.mailwithrsssender.services;

import java.util.Set;

public interface CrudService<T,ID> {
    Set<T> findAll();
    T findById(ID id);
    T save (T t);
    void delete(T o);
    void deleteById(ID id);
    void deleteAll();
}
