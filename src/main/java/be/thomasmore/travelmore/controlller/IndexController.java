package be.thomasmore.travelmore.controlller;


import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.domain.Transportmiddel;
import be.thomasmore.travelmore.service.TransportmiddelService;
import be.thomasmore.travelmore.domain.Locatie;
import be.thomasmore.travelmore.service.LocatieService;
import be.thomasmore.travelmore.domain.Land;
import be.thomasmore.travelmore.service.LandService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "IndexController")
@ManagedBean
@ViewScoped
public class IndexController {

    public List<Transportmiddel> transportmiddelen;
    public List<Locatie> locaties;

    @Inject
    private TransportmiddelService transportmiddelService;
    @Inject
    private LocatieService locatieService;
    @Inject
    private LandService landService;

    public void init() {
        this.transportmiddelen = this.getTransportmiddelen();
        this.locaties = this.getLocaties();
        this.locaties = this.getLocaties();
    }

    public List<Transportmiddel> getTransportmiddelen(){
        return this.transportmiddelService.findAllTransportmiddelen();
    }
    public List<Locatie> getLocaties(){
        return this.locatieService.findAllLocaties();
    }




}
