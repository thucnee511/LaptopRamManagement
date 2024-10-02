package business.daos;

import java.util.List;

public interface IDao<T> {

    boolean save() throws Exception;

    boolean load() throws Exception;

    List<T> get() throws Exception;

    T post(T item) throws Exception;

    T update(T item) throws Exception;

    T delete(T item) throws Exception;
}
