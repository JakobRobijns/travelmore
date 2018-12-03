package be.thomasmore.travelmore.controlller;

import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.service.PersoonService;
import be.thomasmore.travelmore.service.ReisService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class ReisController {
    private Reis gezochteReis = new Reis();

    private List<Reis> zoekResultaat;

    @Inject
    private ReisService reisService;

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

        zoekResultaat = getReizen();

        return "overzichtReizen";
    }
}
