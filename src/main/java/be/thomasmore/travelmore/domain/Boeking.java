package be.thomasmore.travelmore.domain;

import javax.persistence.*;

@Entity
@Table(name = "Boeking")
@NamedQueries(
        {
                @NamedQuery(
                        name = Boeking.FIND_ALL,
                        query = "SELECT l FROM Boeking l"
                )
        }
)
public class Boeking {
    public static final String FIND_ALL = "Boeking.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Persoon persoon;
    @ManyToOne
    private Reis reis;
    @Column(name = "betaald")
    private boolean betaald;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public Reis getReis() {
        return reis;
    }

    public void setReis(Reis reis) {
        this.reis = reis;
    }

    public boolean isBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }
}
