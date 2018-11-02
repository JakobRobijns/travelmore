package be.thomasmore.travelmore.controlller;

import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.service.PersoonService;
import be.thomasmore.travelmore.service.ReisService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "ReisAdminController")
@ManagedBean
@SessionScoped
public class ReisAdminController {
    private Reis reisObj = new Reis();

    @Inject
    private ReisService reisService;

    public Reis getReis() {
        return reisObj;
    }

    public List<Reis> getReizen(){
        return this.reisService.findAllReizen();
    }

    public void setReis(Reis reis){
        reisObj = reis;
    }
    public String ReisAanpassen(Reis reis){
        //reis opvullen...
        setReis(reis);

        //returnen naar edit pagina
        return "editReis?faces-redirect=true";
    }
    public String ReisEdit(){
        //reis aanpassen
        //reisService.update(reisObj);

        //reis leegmaken
        setReis(new Reis());
        //returnen naar edit pagina
        return "reizenAdmin?faces-redirect=true";
    }
    public String ReisVerwijder(Reis reis){
        //reis verwijderen
        this.reisService.delete(reis);

        //reis leegmaken
        setReis(new Reis());
        //returnen naar edit pagina
        return "reizenAdmin?faces-redirect=true";
    }

}
