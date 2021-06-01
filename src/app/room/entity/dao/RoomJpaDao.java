package app.room.entity.dao;

import app.commons.dao.JpaDao;
import app.room.entity.Room;
import app.room.entity.Room;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RoomJpaDao implements JpaDao<Room> {

    private EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    public EntityManager entityManager() {
        return entityManager;
    }

    @Override
    public Room get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Room> getAll() {
        Query query = entityManager.createNamedQuery("Room.findAll");
        return query.getResultList();
    }

    @Override
    public void save(Room e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Room e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Room e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
