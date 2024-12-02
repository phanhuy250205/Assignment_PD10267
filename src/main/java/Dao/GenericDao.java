package Dao;

import java.util.List;

public interface GenericDao<T> {
    List<T> findAll();

    T findById(String id);
    void create(T item);
    void update(T item);
    boolean deleteById(int id);




}
