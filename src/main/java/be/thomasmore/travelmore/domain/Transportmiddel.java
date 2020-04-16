package be.thomasmore.travelmore.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Transportmiddel")
@NamedQueries(
        {
                @NamedQuery(
                        name = Transportmiddel.FIND_ALL,
                        query = "SELECT l FROM Transportmiddel l"
                )
        }
)
public class Transportmiddel {


    public static final String FIND_ALL = "Transportmiddel.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "naam")
    private String naam;
    @OneToMany(mappedBy = "transportmiddel")
    private List<Reis> reizen = new ArrayList<>();

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

    public List<Reis> getReizen() {
        return reizen;
    }

    public void setReizen(List<Reis> reizen) {
        this.reizen = reizen;
    }
}
