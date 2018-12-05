package be.thomasmore.travelmore.controlller;
import be.thomasmore.travelmore.domain.Betaalmethode;
import be.thomasmore.travelmore.domain.Boeking;
import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.service.BetaalmethodeService;
import be.thomasmore.travelmore.service.BoekingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped

public class BoekingController {

    private Boeking newBoeking = new Boeking();
    @Inject
    private BoekingService boekingService;
    @Inject
    private BetaalmethodeService betaalmethodeService;

    public Boeking getNewBoeking() { return newBoeking; }

    public void setNewBoeking(Boeking newBoeking) { this.newBoeking = newBoeking; }

    public List<Betaalmethode> getAllBetaalmethodes(){
        return this.betaalmethodeService.findAllBetaalmethode();
    }

    public String boekReis(Reis reis){
        newBoeking.setReis(reis);
        return "reisBoeken";
    }
}
