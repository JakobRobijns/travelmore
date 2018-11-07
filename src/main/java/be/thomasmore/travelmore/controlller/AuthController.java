package be.thomasmore.travelmore.controlller;

import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.service.PersoonService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class AuthController {
    private Persoon newPersoon = new Persoon();
    private Persoon logedinPersoon = new Persoon();

    @Inject
    private PersoonService persoonService;

    public Persoon getNewPersoon() {
        return newPersoon;
    }

    public void setNewPersoon(Persoon newPersoon) {
        this.newPersoon = newPersoon;
    }

    public Persoon getLogedinPersoon() {
        return logedinPersoon;
    }

    public void setLogedinPersoon(Persoon logedinPersoon) {
        this.logedinPersoon = logedinPersoon;
    }

    public List<Persoon> getPersonen(){
        return this.persoonService.findAllPersonen();
    }

    public void submit(){
        List<Persoon> ingelogdePersoon = persoonService.authPersoon(newPersoon.getEmail(), newPersoon.getWachtwoord());
        if(ingelogdePersoon.size() > 0){
            logedinPersoon = ingelogdePersoon.get(0);
        }//TODO: Error tonen bij ongeldige gegevens
        newPersoon = new Persoon();
    }

    public String afmelden(){
        logedinPersoon = new Persoon();
        return "login";
    }
}
