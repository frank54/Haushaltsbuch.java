package de.bagehorn.Haushaltsbuch.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.bagehorn.Haushaltsbuch.services.BuchungSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidGenerator.class)
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;
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
