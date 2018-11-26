package be.thomasmore.travelmore.controlller;

import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.domain.Locatie;
import be.thomasmore.travelmore.domain.Transportmiddel;
import be.thomasmore.travelmore.service.TransportmiddelService;
import be.thomasmore.travelmore.service.ReisService;
import be.thomasmore.travelmore.service.LocatieService;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.text.*;

@Named(value = "ReisAdminController")
@ManagedBean
@SessionScoped
public class ReisAdminController {

    private Reis reisObj = new Reis();

    @Inject
    private ReisService reisService;
    @Inject
    private LocatieService locatieService;
    @Inject
    private TransportmiddelService transportService;


    public Reis getReis() {
        return reisObj;
    }

    public List<Reis> getReizen(){
        return this.reisService.findAllReizen();
    }
    public List<Locatie> getLocaties(){
        return this.locatieService.findAll();
    }
    public List<Transportmiddel> getTransportmiddelen(){
        return this.transportService.findAll();
    }

    // functies voor het element van het object op de 1ste plaats te zetten in de array
    // dit zodat dit als éérst te zien is in de combobox
    public List<Locatie> getAankomstLocatiesWithReis(){

        List<Locatie> locaties = this.locatieService.findAll();
        Integer index = 0;

        if (reisObj.getAankomstLocatie().getNaam() != null || reisObj.getAankomstLocatie().getNaam() != "" ){
            // reis object is opgevuld dus array sorteren
            Outerloop:
            for (Locatie land : locaties) {
                if (reisObj.getAankomstLocatie().getNaam().equals(land.getNaam())){
                    // zorg dat deze index éést in de lijst komt
                    break Outerloop;
                }
                index++;
            }
        }

        // lijst her-ordenen met index item als eerst
        locaties.add(0, locaties.get(index) );
        locaties.remove(index+1);

        return locaties;

    }
    public List<Locatie> getVertrektLocatiesWithReis(){

        List<Locatie> locaties = this.locatieService.findAll();
        Integer index = 0;

        if (reisObj.getVertrekLocatie().getNaam() != null || reisObj.getVertrekLocatie().getNaam() != "" ){
            // reis object is opgevuld dus array sorteren
            Outerloop:
            for (Locatie land : locaties) {
                if (reisObj.getVertrekLocatie().getNaam().equals(land.getNaam())){
                    // zorg dat deze index éést in de lijst komt
                    break Outerloop;
                }
                index++;
            }
        }

        // lijst her-ordenen met index item als eerst
        locaties.add(0, locaties.get(index) );
        locaties.remove(index+1);

        return locaties;

    }
    public List<Transportmiddel> getTransportsWithReis(){

        List<Transportmiddel> transportmiddelen = this.transportService.findAll();
        Integer index = 0;

        if (reisObj.getTransportmiddel().getNaam() != null || reisObj.getTransportmiddel().getNaam() != "" ){
            // reis object is opgevuld dus array sorteren
            Outerloop:
            for (Transportmiddel transport : transportmiddelen) {
                if (reisObj.getTransportmiddel().getNaam().equals(transport.getNaam())){
                    // zorg dat deze index éést in de lijst komt
                    break Outerloop;
                }
                index++;
            }
        }

        // lijst her-ordenen met index item als eerst
        transportmiddelen.add(0, transportmiddelen.get(index) );
        transportmiddelen.remove(index+1);

        return transportmiddelen;

    }



    public void setReis(Reis reis){
        reisObj = reis;
    }

    public String ReisAanpassen(Reis reis){
        //reis opvullen...
        setReis(reis);

        //returnen naar edit pagina
        return "editReis";
    }
    public String ReisInsert(){
        //reis opvullen...
        // id's worden opggevuld in reis klasse
        Date date = Calendar.getInstance().getTime();
        setReis(new Reis(date, date));

        //returnen naar edit pagina
        return "editReis";
    }
    public String ReisSave(){
        if(reisObj.getId() == 0){
            //reis aanpassen
            reisService.insert(reisObj);
            System.out.println("Inserting reis...");
        }
        else {
            //reis aanpassen
            reisService.update(reisObj);
            System.out.println("Updating reis...");
        }


        //reis leegmaken
        setReis(new Reis());

        //returnen naar edit pagina
        return "reizenAdmin";
    }
    public String ReisVerwijder(Reis reis){
        //reis verwijderen
        this.reisService.delete(reis);

        //reis leegmaken
        setReis(new Reis());
        //returnen naar edit pagina
        return "reizenAdmin";
    }

}
