package com.revature.dao;

import java.util.List;

public interface ICrudDAO <T> {
    void save(T obj);
    void update(T obj);
    void delete(T obj);
    List<T> getAll();
}
