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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public String aanmelden() {
        List<Persoon> ingelogdePersoon = persoonService.authPersoon(newPersoon.getEmail(), this.hashPassword(newPersoon.getWachtwoord()));

        if(ingelogdePersoon.size() > 0){
            logedinPersoon = ingelogdePersoon.get(0);
            errorMsg = "";
            newPersoon = new Persoon();
            return "index";
        } else {
            errorMsg = "Geen account gevonden!";
            return "login";
        }
    }

    public String afmelden(){
        logedinPersoon = new Persoon();
        return "login";
    }

    public String registreren(){
        if(this.persoonService.getByMail(newPersoon.getEmail()).size() == 0){
            newPersoon.setWachtwoord(this.hashPassword(newPersoon.getWachtwoord()));
            this.persoonService.insert(newPersoon);
            newPersoon = new Persoon();
            errorMsg = "";
            return "login";
        } else{
            errorMsg = "Er bestaat al een account met deze e-mail!";
            return "";
        }
    }

    private String hashPassword(String password){
        String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //TODO: Wachtwoord vegeten
}
