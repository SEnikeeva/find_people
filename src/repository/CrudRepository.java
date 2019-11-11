package repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    int save(T model) throws SQLException, IOException, ClassNotFoundException;
    T findByID(int id) throws SQLException, IOException, ClassNotFoundException;
    void delete(T model);
    List<T> findAll() throws SQLException, IOException, ClassNotFoundException;
    void update(T model) throws SQLException, IOException, ClassNotFoundException;
}
