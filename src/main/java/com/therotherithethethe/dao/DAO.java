package com.therotherithethethe.dao;

import com.therotherithethethe.entity.FinalEntity;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DAO<T extends FinalEntity> {

    List<T> findBy(String column, Object value);

    List<T> findAll();

    boolean save(T entity);
    boolean add(T entity);
    boolean update(T entity);
    boolean remove(T entity);
}
