package de.bagehorn.Haushaltsbuch.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Kategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private int position;
    private String typ;
    private String beschreibung;
    @OneToMany(mappedBy = "kategorie")
    private Set<Buchung> buchungen;

    @Version
    private Integer version;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

}
