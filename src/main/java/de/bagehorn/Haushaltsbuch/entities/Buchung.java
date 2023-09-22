package de.bagehorn.Haushaltsbuch.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.bagehorn.Haushaltsbuch.services.BuchungSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonSerialize(using = BuchungSerializer.class)
@Entity
public class Buchung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private String beschreibung;
    private float betrag;
    private Date datum;
    @ManyToOne
    private Kategorie kategorie;

    @Version
    private Integer version;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

}
