package s180377;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class Database<E> {
    private final EntityManagerFactory emf;
    private final Class<E> storedClass;

    public Database(EntityManagerFactory emf, Class<E> storedClass) {
        this.emf = emf;
        this.storedClass = storedClass;
    }

    public void add(E entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
        em.close();

    }
    public void delete(E entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(entity));
        transaction.commit();
        em.close();
    }



    public List<E> getAll() {
        EntityManager em = emf.createEntityManager();
        List<E> all = em.createQuery("select e from " + storedClass.getSimpleName() + " e", storedClass).getResultList();
        em.close();
        return all;
    }

    public List<E> getAllHigher(int height) {
        if(storedClass==Tower.class) {
            EntityManager em = emf.createEntityManager();
            List<E> all = em.createQuery("select e from Tower e where e.height>"+height,storedClass).getResultList();
            em.close();
            return all;
        }
        return null;
    }
}
