
package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.repository.ReisRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ReisService {

    @Inject
    private ReisRepository reisRepository;

    public Reis findReisById(int id) {
        return reisRepository.findById(id);
    }

    public List<Reis> findAllReizen() {
        return reisRepository.findAll();
    }

    public List<Reis> zoekReizen(Reis reis) {
        return reisRepository.zoekReizen(reis);
    }

    public void insert(Reis reis) {
        reisRepository.insert(reis);
    }
    public void update(Reis reis) {
        reisRepository.edit(reis);
    }
    public void delete(Reis reis) {
        reisRepository.remove(reis);
    }
}
