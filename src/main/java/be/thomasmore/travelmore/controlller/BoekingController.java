package be.thomasmore.travelmore.controlller;
import be.thomasmore.travelmore.domain.Betaalmethode;
import be.thomasmore.travelmore.domain.Boeking;
import be.thomasmore.travelmore.domain.Persoon;
import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.service.BetaalmethodeService;
import be.thomasmore.travelmore.service.BoekingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

@ManagedBean
@SessionScoped

public class BoekingController {

    private Boeking newBoeking = new Boeking();
    @Inject
    private BoekingService boekingService;
    @Inject
    private BetaalmethodeService betaalmethodeService;
    @Inject
    private AuthController authController;

    //betaalmethode opslaan (alleen voor mail)
    private Betaalmethode betaalmethod = new Betaalmethode();

    public Betaalmethode getBetaalmethod() {
        return betaalmethod;
    }
    public void setBetaalmethod(Betaalmethode betaalmethod) {
        this.betaalmethod = betaalmethod;
    }

    public Boeking getNewBoeking() { return newBoeking; }
    public void setNewBoeking(Boeking newBoeking) { this.newBoeking = newBoeking; }



    public List<Betaalmethode> getAllBetaalmethodes(){
        return this.betaalmethodeService.findAllBetaalmethode();
    }

    public String boekReis(Reis reis){
        setNewBoeking(new Boeking(authController.getLogedinPersoon(),reis));

        return "reisBoeken";
    }

    public String boekReisOpslaan(Persoon persoon){
        newBoeking.setPersoon(persoon);
        newBoeking.getReis().setPrijs(newBoeking.getReis().getPrijs()*newBoeking.getAantal());

        //Boeking opslaan in database
        boekingService.insert(newBoeking);

        //mail verzenden
        Sendmail(persoon.getEmail());

        //Boeking leegmaken
        newBoeking = new Boeking();

        return "boekingBetaald";
    }

    // send email
    public void Sendmail(String email) {
        // Recipient's email ID needs to be mentioned.
        String to = email;


        String host = "smtp.gmail.com";
        String from = "tienmaaltienis100@gmail.com";
        String pass = "TEAMtien10";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true"); // added this line
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

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
            message.setSubject("Reis "+ getNewBoeking().getReis().getAankomstLocatie().getLand().getNaam() +" geboekt!");

            // set content and define type
            message.setContent("Proficiat met de boeking van je reis voor <b>"+getNewBoeking().getAantal()+"</b> personen. " +
                    "<br/><br/> Je zal vertrekken op " + getNewBoeking().getReis().getVertrekDatum().toString() +
                    " en aankomen op "+ getNewBoeking().getReis().getAankomstDatum().toString() +
                    "<br/><br/> U betaalde: €"+getNewBoeking().getReis().getPrijs()+" met "+ betaalmethod.getNaam() +" <br/> Veel plezier!", "text/html; charset=utf-8");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
