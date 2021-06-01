package app.user.entity.dao;

import app.commons.dao.JpaDao;
import app.user.entity.User;
import app.user.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class UserJpaDao implements JpaDao<User> {
    
    private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    
    @Override
    public EntityManager entityManager() {
        return entityManager;
    }
    
    @Override
    public User get(long id) {
        return entityManager.find(User.class, id);
    }
    
    @Override
    public List<User> getAll() {
        Query query = entityManager.createNamedQuery("User.findAll");
        return query.getResultList();
    }
    
    @Override
    public void save(User user) {
        executeInsideTransaction(em -> em.persist(user));
    }
    
    @Override
    public void update(User user) {
        executeInsideTransaction(em -> em.merge(user));
    }
    
    @Override
    public void delete(User user) {
        executeInsideTransaction(em -> em.remove(em.contains(user)?user:em.merge(user)));
    }
    
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("User.findByUsernameAndPassword");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException exception) {
            System.err.format("Not exist user with username '%s'%n", username);
            return null;
        } catch (NonUniqueResultException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    
    public User findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException exception) {
            System.err.format("Not exist user with username '%s'%n", username);
            return null;
        } catch (NonUniqueResultException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    
}
