package jpa_task.impl.domain.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class CrudOperations<T> {
    private final EntityManager entityManager;
    private final Class<T> clazz;

    public CrudOperations(EntityManager entityManager, Class<T> clazz) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    public void insertEntity(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }

    public T findEntity(int id) {
        entityManager.getTransaction().begin();
        T object = entityManager.find(clazz, id);
        entityManager.getTransaction().commit();
        if (object == null) {
            throw new EntityNotFoundException("Can't find for ID "
                    + id);
        }
        return object;
    }

    public void updateEntity(T t) {
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
    }

    public void removeEntity(int id) {
        entityManager.getTransaction().begin();
        T object  = entityManager.find(clazz, id);
        if (object == null) {
            throw new EntityNotFoundException("Can't find for ID "
                    + id);
        } else {
            entityManager.remove(object);
        }
        entityManager.getTransaction().commit();
    }
}
