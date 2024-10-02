package services;

import java.util.List;

import business.entities.RamModule;

public interface IService<T> {
    List<T> getAll() throws Exception;
    T get(String code) throws Exception;
    T add(T item) throws Exception;
    T update(T item) throws Exception;
    T delete(RamModule item) throws Exception;
    boolean save() throws Exception;
}
