package database;

import java.util.List;

public interface CRUD {
    public Object create (Object obj);
    public boolean update (Object obj);
    public boolean delete (Object obj);
    public List<Object> findAll();
}
