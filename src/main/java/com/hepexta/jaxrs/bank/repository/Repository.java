package com.hepexta.jaxrs.bank.repository;

import java.util.Collection;

public interface Repository<T> {
    Collection<T> getList();
    T findById(String id);
    String insert(T model);
    boolean modify(T model);
    boolean delete(String id);
}
