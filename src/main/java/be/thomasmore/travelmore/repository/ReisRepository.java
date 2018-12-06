package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Reis;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Reis> test(Date vertrekDatum) {
        return entityManager.createNamedQuery(Reis.ZOEK_REIZEN, Reis.class).setParameter("vertrekDatum", vertrekDatum).getResultList();
    }

    public List<Reis> zoekReizen(Reis reis) {
        String queryString = "Select r from Reis r";
        int aantalQuery = 0;
        if (reis.getVertrekLocatie().getId() != 0){
            aantalQuery++;
            queryString += " WHERE r.vertrekLocatie.id = " + reis.getVertrekLocatie().getId();
        }
        if (reis.getAankomstLocatie().getId() != 0){
            aantalQuery++;
            if(aantalQuery > 1){
                queryString += " AND ";
            }else{
                queryString += " WHERE ";
            }
            queryString += " r.aankomstLocatie.id = " + reis.getAankomstLocatie().getId();
        }
        if(reis.getTransportmiddel() != null){
            if (reis.getTransportmiddel().getId() != 0){
                aantalQuery++;
                if(aantalQuery > 1){
                    queryString += " AND ";
                }else{
                    queryString += " WHERE ";
                }
                queryString += " r.transportmiddel.id = " + reis.getTransportmiddel().getId();
            }
        }
        if (reis.getPrijs() != 0){
            aantalQuery++;
            if(aantalQuery > 1){
                queryString += " AND ";
            }else{
                queryString += " WHERE ";
            }
            queryString += " r.prijs <= " + reis.getPrijs();
        }
        if (reis.getAantalPlaatsen() != 0){
            aantalQuery++;
            if(aantalQuery > 1){
                queryString += " AND ";
            }else{
                queryString += " WHERE ";
            }
            queryString += " r.aantalPlaatsen >= " + reis.getAantalPlaatsen();
        }

        if (reis.getAankomstDatum() != null){
            aantalQuery++;
            if(aantalQuery > 1){
                queryString += " AND ";
            }else{
                queryString += " WHERE ";
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String formattedDate = formatter.format(reis.getAankomstDatum());
            queryString += " r.aankomstDatum LIKE  '" + formattedDate + "%'";
        }

        if (reis.getVertrekDatum() != null){
            aantalQuery++;
            if(aantalQuery > 1){
                queryString += " AND ";
            }else{
                queryString += " WHERE ";
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String formattedDate = formatter.format(reis.getVertrekDatum());
            queryString += " r.vertrekDatum LIKE  '" + formattedDate + "%'";
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
