package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.repository.PersoonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PersoonService {

    @Inject
    private PersoonRepository persoonRepository;

    public Persoon findPersoonById(int id) {
        return persoonRepository.findById(id);
    }

    public List<Persoon> findAllPersonen() {
        return persoonRepository.findAll();
    }

    public List<Persoon> authPersoon(String email, String wachtwoord) { return persoonRepository.auth(email, wachtwoord); }

    public List<Persoon> getByMail(String email) { return persoonRepository.getByMail(email); }

    public void insert(Persoon persoon) {
        persoonRepository.insert(persoon);
    }
}
