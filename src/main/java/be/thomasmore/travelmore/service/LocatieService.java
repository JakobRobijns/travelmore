package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Locatie;
import be.thomasmore.travelmore.repository.LocatieRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LocatieService {

    @Inject
    private LocatieRepository locatieRepository;

    public List<Locatie> findAllLocations() {
        return locatieRepository.findAll();
    }

    public List<Locatie> findAll() {
        return locatieRepository.findAll();
    }

    public Locatie findLocationById(int id) {
        return locatieRepository.findById(id);
    }

    public List<Locatie> findAllLocaties() {
        return locatieRepository.findAll();
    }

    public void insert(Locatie locatie) {
        locatieRepository.insert(locatie);
    }


}
