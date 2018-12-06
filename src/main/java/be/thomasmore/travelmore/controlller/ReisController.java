package be.thomasmore.travelmore.controlller;

import be.thomasmore.travelmore.domain.Locatie;
import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.domain.Transportmiddel;
import be.thomasmore.travelmore.service.LocatieService;
import be.thomasmore.travelmore.service.PersoonService;
import be.thomasmore.travelmore.service.ReisService;
import be.thomasmore.travelmore.service.TransportmiddelService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import static org.primefaces.component.api.UITree.PropertyKeys.value;

@ManagedBean
@SessionScoped
public class ReisController {
    public List<Locatie> locaties;

    public List<Transportmiddel> transportmiddelen;

    private Reis gezochteReis = new Reis();

    private List<Reis> zoekResultaat;

    @Inject
    private ReisService reisService;
    @Inject
    private LocatieService locatieService;
    @Inject
    private TransportmiddelService transportmiddelService;

    public void init() {
        this.locaties = this.getLocaties();
        this.transportmiddelen = this.getTransportmiddelen();
    }

    public List<Locatie> getLocaties(){
        return this.locatieService.findAllLocaties();
    }

    public List<Transportmiddel> getTransportmiddelen(){
        return this.transportmiddelService.findAllTransportmiddelen();
    }

    public Reis getGezochteReis() {
        return gezochteReis;
    }

    public void setGezochteReis(Reis gezochteReis) {
        this.gezochteReis = gezochteReis;
    }

    public List<Reis> getZoekResultaat() {
        return zoekResultaat;
    }

    public void setZoekResultaat(List<Reis> zoekResultaat) {
        this.zoekResultaat = zoekResultaat;
    }

    public List<Reis> getReizen(){
        return this.reisService.findAllReizen();
    }

    public String zoekReis(){
        zoekResultaat = this.reisService.zoekReizen(gezochteReis);
        // zoekResultaat = this.reisService.test(gezochteReis.getVertrekDatum());
        return "overzichtReizen";
    }

}
