package be.thomasmore.travelmore.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reis")
public class Reis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "vertrekDatum")
    private Date vertrekDatum;
    @Column(name = "aankomstDatum")
    private Date aankomstDatum;
    @ManyToOne
    private Locatie vertrekLocatie;
    @ManyToOne
    private Locatie aankomstLocatie;
    @ManyToOne
    private Transportmiddel transportmiddel;
    @Column(name = "prijs")
    private float prijs;
    @Column(name = "aantalPlaatsen")
    private int aantalPlaatsen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVertrekDatum() {
        return vertrekDatum;
    }

    public void setVertrekDatum(Date vertrekDatum) {
        this.vertrekDatum = vertrekDatum;
    }

    public Date getAankomstDatum() {
        return aankomstDatum;
    }

    public void setAankomstDatum(Date aankomstDatum) {
        this.aankomstDatum = aankomstDatum;
    }

    public Locatie getVertrekLocatie() {
        return vertrekLocatie;
    }

    public void setVertrekLocatie(Locatie vertrekLocatie) {
        this.vertrekLocatie = vertrekLocatie;
    }

    public Locatie getAankomstLocatie() {
        return aankomstLocatie;
    }

    public void setAankomstLocatie(Locatie aankomstLocatie) {
        this.aankomstLocatie = aankomstLocatie;
    }

    public Transportmiddel getTransportmiddel() {
        return transportmiddel;
    }

    public void setTransportmiddel(Transportmiddel transportmiddel) {
        this.transportmiddel = transportmiddel;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    public int getAantalPlaatsen() {
        return aantalPlaatsen;
    }

    public void setAantalPlaatsen(int aantalPlaatsen) {
        this.aantalPlaatsen = aantalPlaatsen;
    }
}
