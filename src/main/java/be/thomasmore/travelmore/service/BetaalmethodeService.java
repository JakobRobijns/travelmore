package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Betaalmethode;
import be.thomasmore.travelmore.repository.BetaalmethodeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BetaalmethodeService {

    @Inject
    private BetaalmethodeRepository betaalmethodeRepository;

    public Betaalmethode findBetaalmethodeById(int id) {
        return betaalmethodeRepository.findById(id);
    }

    public List<Betaalmethode> findAllBetaalmethode() {
        return betaalmethodeRepository.findAll();
    }

    public void insert(Betaalmethode betaalmethode) {
        betaalmethodeRepository.insert(betaalmethode);
    }
}
