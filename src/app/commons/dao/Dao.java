package app.commons.dao;

import java.util.List;


public interface Dao<E> {
    E get(long id);
    
    List<E> getAll();
    
    void save(E e);
    
    void update(E e);
    
    void delete(E e);
}
