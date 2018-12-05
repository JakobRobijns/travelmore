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
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.text.*;

//email

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



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

    // reis aanpassen & toevoegen (editreis.xhtml)
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

    // opslaan & verwijderen
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


    // send email
    public String Sendmail() {
        // Recipient's email ID needs to be mentioned.
        String to = "thibautjoukes@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "tienmaaltienis100@gmail.com"; // TEAMtien10

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("smtp.gmail.com", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return "";
    }


}
