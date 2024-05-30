package com.shoppingsphere.shoppingsphereservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface BaseService<T, ID>{
    T create(T object);

    T update(T object);

    boolean remove(T object);

    T findById(ID id);

    List<T> findAll();
}
