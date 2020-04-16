package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Betaalmethode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BetaalmethodeRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Betaalmethode findById(int id) {
        return entityManager.find(Betaalmethode.class, id);
    }

    public List<Betaalmethode> findAll() {
        return entityManager.createNamedQuery(Betaalmethode.FIND_ALL, Betaalmethode.class).getResultList();
    }

    public void insert(Betaalmethode betaalmethode) {
        entityManager.persist(betaalmethode);
    }
}
