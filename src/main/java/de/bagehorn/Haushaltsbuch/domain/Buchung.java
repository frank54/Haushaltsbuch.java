package de.bagehorn.Haushaltsbuch.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.bagehorn.Haushaltsbuch.services.BuchungSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(value = AccessLevel.PUBLIC)
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
}
