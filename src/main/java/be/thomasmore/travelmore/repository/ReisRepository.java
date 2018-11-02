package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Reis;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ReisRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Reis findById(int id) {
        return entityManager.find(Reis.class, id);
    }

    public List<Reis> findAll() {
        return entityManager.createNamedQuery(Reis.FIND_ALL, Reis.class).getResultList();
    }

    public void insert(Reis reis) {
        entityManager.persist(reis);
    }
    public void edit(Reis reis) {
        entityManager.merge(reis);
    }
    public void remove(Reis reis) {
        entityManager.remove(entityManager.merge(reis));
    }

}
