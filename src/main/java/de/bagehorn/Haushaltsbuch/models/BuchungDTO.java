package de.bagehorn.Haushaltsbuch.models;

import de.bagehorn.Haushaltsbuch.entities.Kategorie;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BuchungDTO {

    @EqualsAndHashCode.Include
    private Long id;
    private String beschreibung;
    private float betrag;
    private Date datum;
    private Kategorie kategorie;

    private Integer version;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

}
