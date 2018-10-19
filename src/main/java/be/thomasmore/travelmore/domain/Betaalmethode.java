package be.thomasmore.travelmore.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Betaalmethode")
@NamedQueries(
        {
                @NamedQuery(
                        name = Betaalmethode.FIND_ALL,
                        query = "SELECT l FROM Betaalmethode l"
                )
        }
)
public class Betaalmethode {
    public static final String FIND_ALL = "Betaalmethode.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "naam")
    private String naam;
    @ManyToMany
    @JoinTable(name= "PersoonBetaalmethode")
    private List<Persoon> personen = new ArrayList<>();

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

    public List<Persoon> getPersonen() {
        return personen;
    }

    public void setPersonen(List<Persoon> personen) {
        this.personen = personen;
    }
}
