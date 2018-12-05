package be.thomasmore.travelmore.controlller;
import be.thomasmore.travelmore.domain.Boeking;
import be.thomasmore.travelmore.service.BoekingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped

public class BoekingController {

    private Boeking newBoeking = new Boeking();

    @Inject
    private BoekingService boekingService;

    public Boeking getNewBoeking() { return newBoeking; }

    public void setNewBoeking(Boeking newBoeking) { this.newBoeking = newBoeking; }
}
