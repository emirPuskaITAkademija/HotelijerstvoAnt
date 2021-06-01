package app.commons.dao;

import app.commons.constants.Constants;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public interface JpaDao<E> extends Dao<E> {
    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(Constants.PU_NAME);

    public EntityManager entityManager();
    
    default void executeInsideTransaction(Consumer<EntityManager> consumer){
        EntityManager entityManager = entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            consumer.accept(entityManager);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
            throw new RuntimeException(e.getMessage());
        }
        
        
        
        
    }
}
