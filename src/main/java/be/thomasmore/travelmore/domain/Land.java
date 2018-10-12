package be.thomasmore.travelmore.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Land")
public class Land {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "naam")
    private String naam;
    @Column(name = "code")
    private String code;
    @OneToMany(mappedBy = "land")
    private List<Locatie> locaties = new ArrayList<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Locatie> getLocaties() {
        return locaties;
    }

    public void setLocaties(List<Locatie> locaties) {
        this.locaties = locaties;
    }
}
