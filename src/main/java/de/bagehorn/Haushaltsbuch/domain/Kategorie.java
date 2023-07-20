package de.bagehorn.Haushaltsbuch.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Kategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer position;
    private String typ;
    private String beschreibung;
    @OneToMany(mappedBy = "kategorie")
    private Set<Buchung> buchungen = new HashSet<>();

    public Set<Buchung> getBuchungen() {
        return buchungen;
    }

    public void setBuchungen(Set<Buchung> buchungen) {
        this.buchungen = buchungen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getPosition() { return position; }

    public void setPosition(Integer position) { this.position = position; }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kategorie kategorie = (Kategorie) o;

        return Objects.equals(id, kategorie.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
