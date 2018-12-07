package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Boeking;
import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.repository.BoekingRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BoekingService {

    @Inject
    private BoekingRepository boekingRepository;

    public Boeking findBoekingById(int id) {
        return boekingRepository.findById(id);
    }

    public List<Boeking> findAllBoekingen() {
        return boekingRepository.findAll();
    }

    public void insert(Boeking inBoeking) {
        //Boeking boeking = inBoeking;
        boekingRepository.insert(inBoeking);
    }
}
