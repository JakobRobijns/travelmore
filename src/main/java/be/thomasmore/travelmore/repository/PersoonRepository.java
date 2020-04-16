package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Persoon;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PersoonRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Persoon findById(int id) {
        return entityManager.find(Persoon.class, id);
    }

    public List<Persoon> findAll() {
        return entityManager.createNamedQuery(Persoon.FIND_ALL, Persoon.class).getResultList();
    }

    public List<Persoon> auth(String email, String wachtwoord) {
        return entityManager.createNamedQuery(Persoon.AUTH, Persoon.class).setParameter("email", email).setParameter("wachtwoord", wachtwoord).getResultList();
    }

    public List<Persoon> getByMail(String email) {
        return entityManager.createNamedQuery(Persoon.FIND_BY_MAIL, Persoon.class).setParameter("email", email).getResultList();
    }

    public void insert(Persoon persoon) {
        entityManager.persist(persoon);
    }
}
