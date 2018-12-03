package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Reis;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

public class ReisRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Reis findById(int id) {
        return entityManager.find(Reis.class, id);
    }

    public List<Reis> findAll() {
        return entityManager.createNamedQuery(Reis.FIND_ALL, Reis.class).getResultList();
    }

    public List<Reis> zoekReizen(Reis reis) {
        String queryString = "Select r from Reis r";
        if (reis.getVertrekLocatie().getId() != 0){
            queryString += " Where r.vertrekLocatie.id = " + reis.getVertrekLocatie().getId();
        }
        if (reis.getAankomstLocatie().getId() != 0){
            queryString += " And r.aankomstLocatie.id = " + reis.getAankomstLocatie().getId();
        }
        Query query = entityManager.createQuery(queryString);
        return query.getResultList();
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
