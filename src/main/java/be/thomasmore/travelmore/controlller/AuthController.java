package be.thomasmore.travelmore.controlller;

import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.service.PersoonService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@ManagedBean
@SessionScoped
public class AuthController {
    private Persoon newPersoon = new Persoon();
    private Persoon logedinPersoon = new Persoon();
    private String errorMsg = "";



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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String submit() {
        List<Persoon> ingelogdePersoon = persoonService.authPersoon(newPersoon.getEmail(), newPersoon.getWachtwoord());

        if(ingelogdePersoon.size() > 0){
            logedinPersoon = ingelogdePersoon.get(0);
            errorMsg = "";
            newPersoon = new Persoon();
            return "index";
        } else {
            //TODO: Error tonen bij ongeldige gegevens
            errorMsg = "Geen account gevonden!";
            return "login";
        }
    }

    public String afmelden(){
        logedinPersoon = new Persoon();
        return "login";
    }
}
