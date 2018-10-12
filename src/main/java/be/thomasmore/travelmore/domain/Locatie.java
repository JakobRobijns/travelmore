package be.thomasmore.travelmore.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Locatie")
public class Locatie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "naam")
    private String naam;
    @ManyToOne
    private Land land;
    @OneToMany(mappedBy = "vertrekLocatie")
    private List<Reis> vertrekReizen = new ArrayList<>();
    @OneToMany(mappedBy = "aankomstLocatie")
    private List<Reis> aankomstReizen = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public List<Reis> getVertrekReizen() {
        return vertrekReizen;
    }

    public void setVertrekReizen(List<Reis> vertrekReizen) {
        this.vertrekReizen = vertrekReizen;
    }

    public List<Reis> getAankomstReizen() {
        return aankomstReizen;
    }

    public void setAankomstReizen(List<Reis> aankomstReizen) {
        this.aankomstReizen = aankomstReizen;
    }
}
